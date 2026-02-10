package ru.javaops.bootjava.restaurant;

import ru.javaops.bootjava.MatcherFactory;
import ru.javaops.bootjava.restaurant.to.MenuItemTo;
import ru.javaops.bootjava.restaurant.to.RestaurantWithMenuTo;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantWithMenuTestData {

    public static final MatcherFactory.Matcher<RestaurantWithMenuTo> RESTAURANT_WITH_MENU_MATCHER =
            MatcherFactory.usingEqualsComparator(RestaurantWithMenuTo.class);

    public static final int ITALIANO_ID = 1;
    public static final int SUSHI_ID = 2;
    public static final int BURGER_ID = 3;

    public static final RestaurantWithMenuTo italiano =
            new RestaurantWithMenuTo(
                    ITALIANO_ID,
                    "Italiano",
                    List.of(
                            new MenuItemTo(101, "Pizza Margherita", new BigDecimal("9.99")),
                            new MenuItemTo(102, "Pasta Carbonara", new BigDecimal("11.50"))
                    )
            );

    public static final RestaurantWithMenuTo sushi =
            new RestaurantWithMenuTo(
                    SUSHI_ID,
                    "Sushi",
                    List.of(
                            new MenuItemTo(201, "California Roll", new BigDecimal("8.00"))
                    )
            );

    public static final RestaurantWithMenuTo burger =
            new RestaurantWithMenuTo(
                    BURGER_ID,
                    "Burger",
                    List.of(
                            new MenuItemTo(301, "Cheeseburger", new BigDecimal("10.00")),
                            new MenuItemTo(302, "Fries", new BigDecimal("3.50"))
                    )
            );
}
