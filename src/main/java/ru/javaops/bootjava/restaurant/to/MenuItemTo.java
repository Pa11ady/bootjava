package ru.javaops.bootjava.restaurant.to;

import java.math.BigDecimal;

public record MenuItemTo(
        int id,
        String name,
        BigDecimal price
) {
}
