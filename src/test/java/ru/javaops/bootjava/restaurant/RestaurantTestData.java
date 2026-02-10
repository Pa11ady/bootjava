package ru.javaops.bootjava.restaurant;

import ru.javaops.bootjava.MatcherFactory;
import ru.javaops.bootjava.restaurant.model.Restaurant;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final int RESTAURANT_1_ID = 1;
    public static final int RESTAURANT_2_ID = 2;
    public static final int RESTAURANT_3_ID = 3;
    public static final int NOT_FOUND = 100;

    public static final Restaurant restaurant1 =
            new Restaurant(RESTAURANT_1_ID, "Italiano");

    public static final Restaurant restaurant2 =
            new Restaurant(RESTAURANT_2_ID, "Sushi");

    public static final Restaurant restaurant3 =
            new Restaurant(RESTAURANT_3_ID, "Burger");
}
