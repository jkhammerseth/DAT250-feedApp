package com.hvl.feedApp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hvl.feedApp.repository.AgentRepository;
import com.hvl.feedApp.repository.VoteRepository;
import com.hvl.feedApp.repository.PollRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.hvl.feedApp.Poll;
import com.hvl.feedApp.Agent;
import com.hvl.feedApp.Vote;
import com.hvl.feedApp.Enums.Role;


@Configuration
public class TestConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(AgentRepository aRepository, PollRepository pRepository, VoteRepository vRepository) {
        return args -> {
            Agent Geir = new Agent(
                    "69geir420",
                    "geir420@mail.com",
                    "totallyhashedandencryptedpassword",
                    Role.ADMIN);

            Agent Bob = new Agent(
                    "bobleif",
                    "bobleif@gmail.com",
                    "totallyhashedandencryptedpassword",
                    Role.USER);


            Poll testPoll = new Poll();
            testPoll.setQuestion("Is this a current question?");
            testPoll.setOwner(Bob);
            testPoll.setStartTime(LocalDateTime.now());
            testPoll.setEndTime(LocalDateTime.now().plusDays(1));


            Poll testExpiredPoll = new Poll();
            testExpiredPoll.setQuestion("Is this a past question?");
            testExpiredPoll.setOwner(Bob);
            testExpiredPoll.setStartTime(LocalDateTime.now());
            testExpiredPoll.setEndTime(LocalDateTime.now().minusDays(1));

            Poll testFuturePoll = new Poll();
            testFuturePoll.setQuestion("Is this a future question?");
            testFuturePoll.setOwner(Bob);
            testFuturePoll.setStartTime(LocalDateTime.now().plusDays(1));
            testFuturePoll.setEndTime(LocalDateTime.now().plusDays(2));

           // List<Vote> bobsVotes = new ArrayList<Vote>();
            Vote bobsVote = new Vote();
            bobsVote.setPoll(testPoll);
            bobsVote.setVoter(Bob);
            bobsVote.setAnswer(true);
            testPoll.setYesCount(testPoll.getYesCount()+1);

            Vote bobsVote1 = new Vote();
            bobsVote1.setPoll(testExpiredPoll);
            bobsVote1.setVoter(Bob);
            bobsVote1.setAnswer(true);
            testPoll.setYesCount(testPoll.getYesCount()+1);

/*            bobsVotes.add(bobsVote);
            bobsVotes.add(bobsVote1);
            Bob.setGivenVotes(bobsVotes);*/

            Vote geirsVote = new Vote();
            geirsVote.setPoll(testPoll);
            geirsVote.setVoter(Geir);
            geirsVote.setAnswer(true);
            testPoll.setYesCount(testPoll.getYesCount()+1);

            ArrayList<Poll> bobsPolls = new ArrayList<Poll>();
            bobsPolls.add(testPoll);
            bobsPolls.add(testFuturePoll);
            bobsPolls.add(testExpiredPoll);
            Bob.setOwnedPolls(bobsPolls);

            aRepository.saveAll(List.of(Geir,Bob));
            pRepository.saveAll(List.of(testPoll, testFuturePoll, testExpiredPoll));
            vRepository.saveAll(List.of(bobsVote, bobsVote1, geirsVote));

        };
    }
}
