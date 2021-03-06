package springbootboilerplate.application.auth.rest;

import cn.printf.springbootboilerplate.usercontext.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springbootboilerplate.application.APIBaseTest;
import springbootboilerplate.application.auth.JWTTokenStore;
import springbootboilerplate.application.fixture.UserFixture;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TokenControllerTest extends APIBaseTest {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    JWTTokenStore jwtTokenStore;

    @Autowired
    UserFixture userFixture;

    @Test
    public void should_get_validated_token() throws Exception {
        User adminUser = userFixture.createAdminUser();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/v1/token")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        ImmutableMap.of(
                                "username", adminUser.getUsername(),
                                "password", "123456"
                        )
                ));

        this.mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.value", isA(String.class)));
    }

    @Test
    @WithMockUser(username = "zhangsan", roles = {})
    public void get_user_info() throws Exception {
        User adminUser = userFixture.createAdminUser();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/v1/token/info")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(adminUser.getUsername())))
                .andExpect(jsonPath("$.authorities", hasItems()));
    }
}
