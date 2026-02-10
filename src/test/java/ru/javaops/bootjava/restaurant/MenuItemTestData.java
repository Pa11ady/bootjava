package ru.javaops.bootjava.restaurant;


import ru.javaops.bootjava.MatcherFactory;
import ru.javaops.bootjava.restaurant.to.MenuItemResponseTo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MenuItemTestData {

    public static final MatcherFactory.Matcher<MenuItemResponseTo> MENU_ITEM_MATCHER =
            MatcherFactory.usingEqualsComparator(MenuItemResponseTo.class);

    public static final int NOT_FOUND = 100;
    public static final int MENU_ITEM_ID = 101;
    public static final int RESTAURANT_ID = 1;

    public static final MenuItemResponseTo menuItem1 =
            new MenuItemResponseTo(
                    101,
                    1,
                    "Pizza Margherita",
                    new BigDecimal("9.99"),
                    LocalDate.now()
            );

    public static final MenuItemResponseTo menuItem2 =
            new MenuItemResponseTo(
                    102,
                    1,
                    "Pasta Carbonara",
                    new BigDecimal("11.50"),
                    LocalDate.now()
            );
}
