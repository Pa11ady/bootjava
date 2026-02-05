package ru.javaops.bootjava.restaurant.to;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MenuItemAdminTo(
        int id,
        int restaurantId,
        String name,
        BigDecimal price,
        LocalDate date
) {
}
