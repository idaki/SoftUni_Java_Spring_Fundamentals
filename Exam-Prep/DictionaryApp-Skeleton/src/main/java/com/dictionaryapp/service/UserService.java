package com.dictionaryapp.service;


import com.dictionaryapp.model.dto.RegisterDto;

public interface UserService {
    Boolean register(RegisterDto registerDto);
}
