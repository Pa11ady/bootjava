package ru.javaops.bootjava.restaurant.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.bootjava.restaurant.to.MenuItemTo;
import ru.javaops.bootjava.restaurant.to.RestaurantWithMenuTo;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @GetMapping
    public List<RestaurantWithMenuTo> getAllWithMenuToday() {
        return List.of(
                new RestaurantWithMenuTo(
                        1,
                        "Italiano",
                        List.of(
                                new MenuItemTo(101, "Pizza", new BigDecimal("9.99")),
                                new MenuItemTo(102, "Pasta", new BigDecimal("11.50"))
                        )
                ),
                new RestaurantWithMenuTo(
                        2,
                        "Sushi Place",
                        List.of(
                                new MenuItemTo(201, "California Roll", new BigDecimal("8.00"))
                        )
                )
        );
    }
}
