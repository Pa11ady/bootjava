package ru.javaops.bootjava.vote.to;

import jakarta.validation.constraints.Positive;

public record VoteRequestTo(
        @Positive
        int restaurantId
) {
}
