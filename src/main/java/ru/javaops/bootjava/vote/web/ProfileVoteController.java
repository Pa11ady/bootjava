package ru.javaops.bootjava.vote.web;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.vote.to.VoteRequestTo;
import ru.javaops.bootjava.vote.to.VoteResponseTo;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/profile/votes")
public class ProfileVoteController {

    @GetMapping
    public VoteResponseTo getTodayVote() {
        //как будто пользователь уже голосовал
        return new VoteResponseTo(
                1,
                LocalDate.now(),
                LocalTime.of(10, 15)
        );
    }

    @PostMapping
    public VoteResponseTo vote(@Valid @RequestBody VoteRequestTo request) {
        //как будто голос успешно принят
        return new VoteResponseTo(
                request.restaurantId(),
                LocalDate.now(),
                LocalTime.now()
        );
    }
}
