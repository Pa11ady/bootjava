package ru.javaops.bootjava.restaurant.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record MenuItemRequestTo(

        @NotNull
        Integer restaurantId,

        @NotBlank
        @Size(min = 2, max = 120)
        String name,

        @NotNull
        @Positive
        BigDecimal price
) {
}
