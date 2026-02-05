package ru.javaops.bootjava.vote.to;

import java.time.LocalDate;
import java.time.LocalTime;

public record VoteResponseTo(
        int restaurantId,
        LocalDate date,
        LocalTime time
) {
}
