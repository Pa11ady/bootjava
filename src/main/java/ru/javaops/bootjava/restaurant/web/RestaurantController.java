package ru.javaops.bootjava.restaurant.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.bootjava.restaurant.repository.MenuItemRepository;
import ru.javaops.bootjava.restaurant.to.RestaurantWithMenuTo;
import ru.javaops.bootjava.restaurant.util.RestaurantsUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final MenuItemRepository menuItemRepository;

    @GetMapping
    public List<RestaurantWithMenuTo> getAllWithMenuToday() {
        return RestaurantsUtil.getRestaurantsWithMenuToday(
                menuItemRepository.findAllByDate(LocalDate.now())
        );
    }
}
