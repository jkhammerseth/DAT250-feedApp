package com.hvl.feedApp.poll;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PollConfig {
    @Bean
    CommandLineRunner commandLineRunner(PollRepository repository){
        return args -> {
            Poll poll_1 = new Poll(
                   4,
                    1,
                    "Is this poll active?",
                    LocalDate.of(2022, Month.OCTOBER, 4),
                    LocalDate.of(2022, Month.DECEMBER, 1));
            Poll poll_2 = new Poll(
                    49,
                    24,
                    "Is this poll expired?",
                    LocalDate.of(2021, Month.NOVEMBER, 8),
                    LocalDate.of(2022, Month.JANUARY, 1));
            Poll poll_3 = new Poll(
                    0,
                    0,
                    "Is this poll future?",
                    LocalDate.of(2023, Month.JANUARY, 1),
                    LocalDate.of(2023, Month.JANUARY, 30));
            repository.saveAll(List.of(poll_1,poll_2,poll_3));
        };
    }
}
