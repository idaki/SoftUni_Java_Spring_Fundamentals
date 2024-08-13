package com.dictionaryapp.service;


import com.dictionaryapp.model.dto.AddWordDTO;

public interface WordsService {
    void add(AddWordDTO data);

    void delete(String id);
}
