package com.hvl.feedApp.voteUser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class VoteUserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(VoteUserRepository repository) {
        return args -> {
            VoteUser Geir = new VoteUser(
                    "69geir420",
                    "geir420@mail.com");
            VoteUser Bob = new VoteUser(
                    "bobleif",
                    "bobleif@gmail.com");
            repository.saveAll(List.of(Geir,Bob));
        };
    }
}
