package ru.javaops.bootjava.vote.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.AbstractControllerTest;
import ru.javaops.bootjava.common.util.JsonUtil;
import ru.javaops.bootjava.vote.to.VoteRequestTo;
import ru.javaops.bootjava.vote.to.VoteResponseTo;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javaops.bootjava.user.UserTestData.GUEST_MAIL;
import static ru.javaops.bootjava.user.UserTestData.USER_MAIL;
import static ru.javaops.bootjava.vote.VoteTestData.*;

class ProfileVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/api/profile/votes";

    @Test
    @WithUserDetails(GUEST_MAIL)
    void getTodayVote() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getTodayVoteUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(USER_MAIL)
    void vote() throws Exception {
        VoteRequestTo request = new VoteRequestTo(RESTAURANT_1_ID);

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        VoteResponseTo response = VOTE_MATCHER.readFromJson(action);

        assertThat(response.restaurantId()).isEqualTo(RESTAURANT_1_ID);
        assertThat(response.date()).isEqualTo(LocalDate.now());
    }

    @Test
    @WithUserDetails(USER_MAIL)
    void voteRestaurantNotFound() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(new VoteRequestTo(NOT_FOUND))))
                .andExpect(status().isNotFound());
    }

    @Test
    void voteUnauth() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(new VoteRequestTo(RESTAURANT_1_ID))))
                .andExpect(status().isUnauthorized());
    }
}
