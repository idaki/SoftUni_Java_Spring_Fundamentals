package com.dictionaryapp.service.impl;


import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.service.LanguageService;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final String GERMAN_DESCRIPTION =  "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.";
    private final String SPANISH_DESCRIPTION  = "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.";
    private final String FRENCH_DESCRIPTION = "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.";
    private final String ITALIAN_DESCRIPTION = "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.";



    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void init() {
        if (languageRepository.count() == 0) {
            LanguageName[] languageEnums = LanguageName.values();
            for (LanguageName languageEnum : languageEnums) {
                Language language = new Language();
                language.setLanguageName(languageEnum);
                if (languageEnum.name().equals("GERMAN")){
                    language.setDescription(GERMAN_DESCRIPTION);
                } else if (languageEnum.name().equals("SPANISH")){
                    language.setDescription(SPANISH_DESCRIPTION);
                }else if (languageEnum.name().equals("FRENCH")){
                    language.setDescription(FRENCH_DESCRIPTION);
                }else if (languageEnum.name().equals("ITALIAN")){
                    language.setDescription(ITALIAN_DESCRIPTION);
                }
                languageRepository.save(language);
            }
        }
    }
}
