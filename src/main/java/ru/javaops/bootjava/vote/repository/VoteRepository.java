package ru.javaops.bootjava.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaops.bootjava.vote.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByUserIdAndDate(int userId, LocalDate date);
}
