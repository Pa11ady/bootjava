package ru.javaops.bootjava.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaops.bootjava.restaurant.model.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findAllByRestaurantIdAndDate(int restaurantId, LocalDate date);

    List<MenuItem> findAllByDate(LocalDate date);
}
