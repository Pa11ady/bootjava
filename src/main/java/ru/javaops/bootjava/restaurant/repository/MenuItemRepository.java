package ru.javaops.bootjava.restaurant.repository;

import org.springframework.data.jpa.repository.Query;
import ru.javaops.bootjava.common.BaseRepository;
import ru.javaops.bootjava.restaurant.model.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemRepository extends BaseRepository<MenuItem> {

    List<MenuItem> findAllByRestaurantIdAndDate(int restaurantId, LocalDate date);

    @Query("""
        SELECT mi
        FROM MenuItem mi
        JOIN FETCH mi.restaurant
        WHERE mi.date = :date
    """)
    List<MenuItem> findAllByDateWithRestaurant(LocalDate date);
}
