package com.hvl.feedApp.config;

import com.hvl.feedApp.repository.VoteUserRepository;
import com.hvl.feedApp.VoteUser;
import com.hvl.feedApp.Poll;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
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
/*            Poll testPoll = new Poll();
            testPoll.setQuestion("Is this a question?");
            testPoll.setOwner(Bob);

            ArrayList<Poll> bobsPolls = new ArrayList<Poll>();
            bobsPolls.add(testPoll);
            Bob.setOwnedPolls(bobsPolls);*/

            repository.saveAll(List.of(Geir,Bob));

        };
    }
}
