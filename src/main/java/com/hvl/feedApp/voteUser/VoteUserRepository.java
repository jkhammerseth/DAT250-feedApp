package com.hvl.feedApp.voteUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteUserRepository extends JpaRepository<VoteUser, Long> {
}
