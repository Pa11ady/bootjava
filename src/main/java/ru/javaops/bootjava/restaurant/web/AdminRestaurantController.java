package ru.javaops.bootjava.restaurant.web;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    public record RestaurantAdminTo(int id, String name) {}

    @GetMapping
    public List<RestaurantAdminTo> getAll() {
        return List.of(
                new RestaurantAdminTo(1, "Italiano"),
                new RestaurantAdminTo(2, "Sushi")
        );
    }

    @PostMapping
    public RestaurantAdminTo create(@RequestBody RestaurantAdminTo to) {
        return new RestaurantAdminTo(3, to.name());
    }

    @PutMapping("/{id}")
    public RestaurantAdminTo update(@PathVariable int id, @RequestBody RestaurantAdminTo to) {
        return new RestaurantAdminTo(id, to.name());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        // no content
    }
}
