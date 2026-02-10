package ru.javaops.bootjava.restaurant.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.restaurant.model.MenuItem;
import ru.javaops.bootjava.restaurant.model.Restaurant;
import ru.javaops.bootjava.restaurant.repository.MenuItemRepository;
import ru.javaops.bootjava.restaurant.repository.RestaurantRepository;
import ru.javaops.bootjava.restaurant.to.MenuItemRequestTo;
import ru.javaops.bootjava.restaurant.to.MenuItemResponseTo;
import ru.javaops.bootjava.restaurant.util.MenuItemsUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/menu-items")
@RequiredArgsConstructor
public class AdminMenuItemController {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping
    public List<MenuItemResponseTo> getAll(
            @RequestParam int restaurantId,
            @RequestParam LocalDate date
    ) {
        return MenuItemsUtil.createTos(
                menuItemRepository.findAllByRestaurantIdAndDate(restaurantId, date)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItemResponseTo create(@Valid @RequestBody MenuItemRequestTo to) {
        Restaurant restaurant = restaurantRepository.getExisted(to.restaurantId());
        MenuItem item = MenuItemsUtil.createNewFromTo(to, restaurant);
        menuItemRepository.save(item);
        return MenuItemsUtil.createTo(item);
    }

    @PutMapping("/{id}")
    public MenuItemResponseTo update(@PathVariable int id,
                                     @Valid @RequestBody MenuItemRequestTo to) {
        MenuItem item = menuItemRepository.getExisted(id);
        MenuItemsUtil.updateFromTo(item, to);
        menuItemRepository.save(item);
        return MenuItemsUtil.createTo(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        menuItemRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public MenuItemResponseTo get(@PathVariable int id) {
        MenuItem item = menuItemRepository.getExisted(id);
        return MenuItemsUtil.createTo(item);
    }
}
