package ru.javaops.bootjava.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javaops.bootjava.restaurant.model.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findAllByRestaurantIdAndDate(int restaurantId, LocalDate date);

    @Query("""
        SELECT mi
        FROM MenuItem mi
        JOIN FETCH mi.restaurant
        WHERE mi.date = :date
    """)
    List<MenuItem> findAllByDateWithRestaurant(LocalDate date);
}
