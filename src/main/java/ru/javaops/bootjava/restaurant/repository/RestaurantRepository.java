package ru.javaops.bootjava.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaops.bootjava.restaurant.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
