package ru.javaops.bootjava.restaurant.to;

import java.util.List;

public record RestaurantWithMenuTo(
        int id,
        String name,
        List<MenuItemTo> menu
) {
}
