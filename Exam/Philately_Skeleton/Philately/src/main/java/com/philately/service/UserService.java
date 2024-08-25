package com.philately.service;



import com.philately.config.UserSession;
import com.philately.model.dto.LoginDto;
import com.philately.model.dto.RegisterDto;
import com.philately.model.entity.User;
import com.philately.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }


    public Boolean login(LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByUsername(loginDto.getUsername());

        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return false;
        }

        userSession.login(user);

        return true;
    }



    public void logout() {
        userSession.logout();
    }


    public Boolean register(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return false;
        }
        boolean isUsernameOrEmailTaken = userRepository.existsByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail());

        if (isUsernameOrEmailTaken) {
            return false;
        }
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }



}
