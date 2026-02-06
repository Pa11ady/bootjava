package ru.javaops.bootjava.restaurant.util;

import lombok.experimental.UtilityClass;
import ru.javaops.bootjava.restaurant.model.MenuItem;
import ru.javaops.bootjava.restaurant.model.Restaurant;
import ru.javaops.bootjava.restaurant.to.MenuItemRequestTo;
import ru.javaops.bootjava.restaurant.to.MenuItemResponseTo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MenuItemsUtil {

    public static MenuItem createNewFromTo(MenuItemRequestTo to, Restaurant restaurant) {
        MenuItem item = new MenuItem();
        item.setRestaurant(restaurant);
        item.setName(to.name());
        item.setPrice(to.price());
        item.setDate(LocalDate.now());
        return item;
    }

    public static MenuItem updateFromTo(MenuItem item, MenuItemRequestTo to) {
        item.setName(to.name());
        item.setPrice(to.price());
        return item;
    }

    public static MenuItemResponseTo createTo(MenuItem item) {
        return new MenuItemResponseTo(
                item.getId(),
                item.getRestaurant().getId(),
                item.getName(),
                item.getPrice(),
                item.getDate()
        );
    }

    public static List<MenuItemResponseTo> createTos(Collection<MenuItem> items) {
        return items.stream()
                .map(MenuItemsUtil::createTo)
                .collect(Collectors.toList());
    }
}
