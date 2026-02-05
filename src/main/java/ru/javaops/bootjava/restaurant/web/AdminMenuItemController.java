package ru.javaops.bootjava.restaurant.web;

import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.restaurant.to.MenuItemRequestTo;
import ru.javaops.bootjava.restaurant.to.MenuItemResponseTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/menu-items")
public class AdminMenuItemController {

    @GetMapping
    public List<MenuItemResponseTo> getAll(
            @RequestParam int restaurantId,
            @RequestParam LocalDate date
    ) {
        return List.of(
                new MenuItemResponseTo(
                        101,
                        restaurantId,
                        "Pizza",
                        new BigDecimal("9.99"),
                        date
                )
        );
    }

    @PostMapping
    public MenuItemResponseTo create(@RequestBody MenuItemRequestTo to) {
        return new MenuItemResponseTo(
                102,
                to.restaurantId(),
                to.name(),
                to.price(),
                LocalDate.now()
        );
    }

    @PutMapping("/{id}")
    public MenuItemResponseTo update(@PathVariable int id, @RequestBody MenuItemRequestTo to) {
        return new MenuItemResponseTo(
                id,
                to.restaurantId(),
                to.name(),
                to.price(),
                LocalDate.now()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
    }
}
