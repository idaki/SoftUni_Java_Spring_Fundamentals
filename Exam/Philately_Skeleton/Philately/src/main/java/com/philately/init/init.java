package com.philately.init;

import com.philately.service.PaperService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class init implements CommandLineRunner {



private final PaperService paperService;

    public init(PaperService paperService) {
        this.paperService = paperService;
    }


    @Override
    public void run(String... args) throws Exception {
        paperService.init();
    }
}