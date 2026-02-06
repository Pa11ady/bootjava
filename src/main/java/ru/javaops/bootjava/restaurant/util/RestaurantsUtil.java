package ru.javaops.bootjava.restaurant.util;

import lombok.experimental.UtilityClass;
import ru.javaops.bootjava.restaurant.model.MenuItem;
import ru.javaops.bootjava.restaurant.model.Restaurant;
import ru.javaops.bootjava.restaurant.to.MenuItemTo;
import ru.javaops.bootjava.restaurant.to.RestaurantWithMenuTo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantsUtil {

    public static List<RestaurantWithMenuTo> getRestaurantsWithMenuToday(Collection<MenuItem> items) {

        Map<Restaurant, List<MenuItemTo>> menuByRestaurant =
                items.stream()
                        .collect(Collectors.groupingBy(
                                MenuItem::getRestaurant,
                                Collectors.mapping(RestaurantsUtil::createMenuItemTo, Collectors.toList())
                        ));

        return menuByRestaurant.entrySet().stream()
                .map(entry -> {
                    Restaurant restaurant = entry.getKey();
                    return new RestaurantWithMenuTo(
                            restaurant.getId(),
                            restaurant.getName(),
                            entry.getValue()
                    );
                })
                .collect(Collectors.toList());
    }

    private static MenuItemTo createMenuItemTo(MenuItem item) {
        return new MenuItemTo(
                item.getId(),
                item.getName(),
                item.getPrice()
        );
    }
}
