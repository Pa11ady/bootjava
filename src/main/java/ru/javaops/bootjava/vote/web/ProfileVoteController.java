package ru.javaops.bootjava.vote.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.app.AuthUser;
import ru.javaops.bootjava.vote.model.Vote;
import ru.javaops.bootjava.vote.repository.VoteRepository;
import ru.javaops.bootjava.vote.service.VoteService;
import ru.javaops.bootjava.vote.to.VoteRequestTo;
import ru.javaops.bootjava.vote.to.VoteResponseTo;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/profile/votes")
@RequiredArgsConstructor
public class ProfileVoteController {

    private final VoteRepository voteRepository;
    private final VoteService voteService;

    @GetMapping
    public ResponseEntity<VoteResponseTo> getTodayVote(@AuthenticationPrincipal AuthUser user) {
        return ResponseEntity.of(
                voteRepository.findByUserIdAndDate(user.id(), LocalDate.now())
                        .map(this::toResponse)
        );
    }

    @PostMapping
    public VoteResponseTo vote(@AuthenticationPrincipal AuthUser user,
                               @Valid @RequestBody VoteRequestTo request) {

        Vote vote = voteService.vote(user.id(), request.restaurantId());
        return toResponse(vote);
    }

    private VoteResponseTo toResponse(Vote vote) {
        return new VoteResponseTo(
                vote.getRestaurant().getId(),
                vote.getDate(),
                vote.getTime()
        );
    }
}
