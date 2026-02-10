package ru.javaops.bootjava.restaurant.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.AbstractControllerTest;
import ru.javaops.bootjava.common.util.JsonUtil;
import ru.javaops.bootjava.restaurant.repository.MenuItemRepository;
import ru.javaops.bootjava.restaurant.to.MenuItemRequestTo;
import ru.javaops.bootjava.restaurant.to.MenuItemResponseTo;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javaops.bootjava.restaurant.MenuItemTestData.*;
import static ru.javaops.bootjava.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.bootjava.user.UserTestData.USER_MAIL;

class AdminMenuItemControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/api/admin/menu-items";

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + MENU_ITEM_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(menuItem1));
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void getAllByRestaurantAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT_ID))
                .param("date", LocalDate.now().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(menuItem2, menuItem1));
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void create() throws Exception {
        MenuItemRequestTo request = new MenuItemRequestTo(
                RESTAURANT_ID,
                "New Dish",
                new BigDecimal("12.50")
        );

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(request)))
                .andDo(print())
                .andExpect(status().isCreated());

        MenuItemResponseTo created = MENU_ITEM_MATCHER.readFromJson(action);

        assertThat(created.name()).isEqualTo("New Dish");
        assertThat(created.restaurantId()).isEqualTo(RESTAURANT_ID);
        assertThat(created.date()).isEqualTo(LocalDate.now());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void createInvalid() throws Exception {
        MenuItemRequestTo invalid = new MenuItemRequestTo(
                RESTAURANT_ID,
                "",
                null
        );

        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void createRestaurantNotFound() throws Exception {
        MenuItemRequestTo request = new MenuItemRequestTo(
                NOT_FOUND,
                "Dish",
                new BigDecimal("10.00")
        );

        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(request)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void update() throws Exception {
        MenuItemRequestTo request = new MenuItemRequestTo(
                RESTAURANT_ID,
                "Updated Dish",
                new BigDecimal("15.00")
        );

        perform(MockMvcRequestBuilders.put(REST_URL + "/" + MENU_ITEM_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(request)))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(menuItemRepository.getExisted(MENU_ITEM_ID).getName())
                .isEqualTo("Updated Dish");
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void updateNotFound() throws Exception {
        MenuItemRequestTo request = new MenuItemRequestTo(
                RESTAURANT_ID,
                "Dish",
                new BigDecimal("10.00")
        );

        perform(MockMvcRequestBuilders.put(REST_URL + "/" + NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(request)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + MENU_ITEM_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThat(menuItemRepository.findById(MENU_ITEM_ID)).isEmpty();
    }

    @Test
    @WithUserDetails(ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + MENU_ITEM_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + MENU_ITEM_ID))
                .andExpect(status().isForbidden());
    }
}
