package ru.javaops.bootjava.vote.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.restaurant.model.Restaurant;
import ru.javaops.bootjava.restaurant.repository.RestaurantRepository;
import ru.javaops.bootjava.user.model.User;
import ru.javaops.bootjava.user.repository.UserRepository;
import ru.javaops.bootjava.vote.model.Vote;
import ru.javaops.bootjava.vote.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class VoteService {

    private static final LocalTime DEADLINE = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public Vote vote(int userId, int restaurantId) {
        User user = userRepository.getExisted(userId);
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);

        return voteRepository.findByUserIdAndDate(user.getId(), today)
                .map(existing -> {
                    if (existing.getTime().isAfter(DEADLINE)) {
                        throw new IllegalStateException("Too late to change vote");
                    }
                    existing.setRestaurant(restaurant);
                    existing.setTime(now);
                    return existing; // managed → UPDATE
                })
                .orElseGet(() -> {
                    Vote vote = new Vote();
                    vote.setUser(user);
                    vote.setRestaurant(restaurant);
                    vote.setDate(today);
                    vote.setTime(now);
                    return voteRepository.save(vote);
                });
    }
}
