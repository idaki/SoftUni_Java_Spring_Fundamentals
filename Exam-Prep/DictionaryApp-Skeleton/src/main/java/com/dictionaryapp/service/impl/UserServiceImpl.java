package com.dictionaryapp.service.impl;


import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.LoginDto;
import com.dictionaryapp.model.dto.RegisterDto;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    @Override
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

    @Override
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

    @Override
    public void logout() {
        userSession.logout();
    }


}
