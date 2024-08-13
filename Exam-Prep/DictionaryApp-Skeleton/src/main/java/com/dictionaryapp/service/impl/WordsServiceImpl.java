package com.dictionaryapp.service.impl;


import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordsService;
import org.springframework.stereotype.Service;

@Service
public class WordsServiceImpl implements WordsService {

    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final WordRepository wordRepository;

    public WordsServiceImpl(LanguageRepository languageRepository, UserRepository userRepository, UserSession userSession, WordRepository wordRepository) {
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.wordRepository = wordRepository;
    }


    @Override
    public void add(AddWordDTO data) {

    }

    @Override
    public void delete(String id) {
    }
}
