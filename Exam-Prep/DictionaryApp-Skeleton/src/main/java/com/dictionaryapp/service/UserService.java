package com.dictionaryapp.service;


import com.dictionaryapp.model.dto.LoginDto;
import com.dictionaryapp.model.dto.RegisterDto;

public interface UserService {
    Boolean register(RegisterDto registerDto);

    Boolean login(LoginDto loginDto);
}
