package com.philately.service;

import com.philately.model.dto.PaperTypeDTO;
import com.philately.model.entity.Paper;
import com.philately.model.entity.PaperType;
import com.philately.repository.PaperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {
    private final String WOVE_PAPER =  "Has an even texture without any particular distinguishing features.";
    private final String LAID_PAPER  = "When held up to the light, shows parallel lines of greater or less width running across the stamp.";
    private final String GRANITE_PAPER = "Has tiny specks of coloured fibre in it, which can usually be seen with the naked eye.";

    private final PaperRepository paperRepository;

    public PaperService(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public void init() {
        if (paperRepository.count() == 0) {
            PaperType[] paperEnums = PaperType.values();
            for (PaperType paperEnum : paperEnums) {
                Paper paper = new Paper();
                paper.setPaperName(paperEnum);
                if (paperEnum.name().equals("WOVE_PAPER")){
                    paper.setName("WOVE_PAPER");
                    paper.setDescription(WOVE_PAPER);
                } else if (paperEnum.name().equals("LAID_PAPER")){
                    paper.setName("LAID_PAPER");
                    paper.setDescription(LAID_PAPER);
                }else if (paperEnum.name().equals("GRANITE_PAPER")) {
                    paper.setName("GRANITE_PAPER");
                    paper.setDescription(GRANITE_PAPER);
                }
                paperRepository.save(paper);
            }
        }
    }


}
