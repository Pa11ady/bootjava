package ru.javaops.bootjava.restaurant.web;

import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.restaurant.to.MenuItemAdminTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/menu-items")
public class AdminMenuItemController {

    @GetMapping
    public List<MenuItemAdminTo> getAll(
            @RequestParam int restaurantId,
            @RequestParam LocalDate date
    ) {
        return List.of(
                new MenuItemAdminTo(
                        101,
                        restaurantId,
                        "Pizza",
                        new BigDecimal("9.99"),
                        date
                )
        );
    }

    @PostMapping
    public MenuItemAdminTo create(@RequestBody MenuItemAdminTo to) {
        return new MenuItemAdminTo(
                102,
                to.restaurantId(),
                to.name(),
                to.price(),
                to.date()
        );
    }

    @PutMapping("/{id}")
    public MenuItemAdminTo update(@PathVariable int id, @RequestBody MenuItemAdminTo to) {
        return new MenuItemAdminTo(
                id,
                to.restaurantId(),
                to.name(),
                to.price(),
                to.date()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
    }
}
