// package com.example.dailycard.api;
//
// import com.example.dailycard.data.UserTestData;
// import com.example.dailycard.dto.CreateUserRequest;
// import com.example.dailycard.dto.LoginRequest;
// import com.example.dailycard.dto.UserView;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import lombok.extern.slf4j.Slf4j;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
// import javax.xml.bind.ValidationException;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.hamcrest.Matchers.containsString;
//
// @SpringBootTest
// @AutoConfigureMockMvc
// @Slf4j
// public class TestAuthApi {
//
//    @Autowired private MockMvc mockMvc;
//    @Autowired private ObjectMapper objectMapper;
//    @Autowired private UserTestData userTestData;
//
//    private final String password = "TestPassword";
//    private final String username = "devbelly";
//
//    @Test
//    void test_login_success() throws Exception {
//        UserView userView = userTestData.createUser(username, password);
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsernameOrEmail(userView.getUsername());
//        loginRequest.setPassword(password);
//
//        String content = objectMapper.writeValueAsString(loginRequest);
//
//        MvcResult mvcResult =
//                mockMvc.perform(
//                                post("/api/auth/login")
//                                        .contentType(MediaType.APPLICATION_JSON)
//                                        .content(content))
//                        .andExpect(status().isOk())
//                        .andExpect(header().exists(HttpHeaders.AUTHORIZATION))
//                        .andReturn();
//
//        UserView authUserView =
//                objectMapper.readValue(
//                        mvcResult.getResponse().getContentAsString(), UserView.class);
//        assertEquals(userView.getId(), authUserView.getId(), "User id must match");
//    }
//
//    @Test
//    void test_login_fail() throws Exception {
//        UserView userView = userTestData.createUser(username, password);
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsernameOrEmail(username);
//        loginRequest.setPassword("abc");
//
//        String content = objectMapper.writeValueAsString(loginRequest);
//
//        MvcResult mvcResult =
//                mockMvc.perform(
//                                post("/api/auth/login")
//                                        .contentType(MediaType.APPLICATION_JSON)
//                                        .content(content))
//                        .andExpect(status().isUnauthorized())
//                        .andExpect(header().doesNotExist(HttpHeaders.AUTHORIZATION))
//                        .andReturn();
//    }
//
//    @Test
//    void test_register_success() throws Exception {
//        CreateUserRequest request = new CreateUserRequest();
//        request.setPassword(password);
//        request.setRePassword(password);
//        request.setUsername(username);
//
//        String content = objectMapper.writeValueAsString(request);
//
//        MvcResult mvcResult =
//                mockMvc.perform(
//                                post("/api/auth/register")
//                                        .contentType(MediaType.APPLICATION_JSON)
//                                        .content(content))
//                        .andExpect(status().isOk())
//                        .andReturn();
//
//        UserView userView =
//                objectMapper.readValue(
//                        mvcResult.getResponse().getContentAsString(), UserView.class);
//        assertNotNull(userView.getId(), "User id must not be null");
//    }
//
//    @Test
//    public void testRegisterFail() throws Exception {
//        CreateUserRequest badRequest = new CreateUserRequest();
//        badRequest.setUsername("invalid.username");
//
//        this.mockMvc
//                .perform(
//                        post("/api/auth/register")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(toJson(objectMapper, badRequest)))
//                .andExpect(status().isBadRequest());
//    }
//
//    public static <T> String toJson(ObjectMapper objectMapper, T object)
//            throws JsonProcessingException {
//        return objectMapper.writeValueAsString(object);
//    }
//
//    @Test
//    void temporary() throws JsonProcessingException {
//        CreateUserRequest request = new CreateUserRequest();
//        request.setPassword(password);
//        request.setRePassword(password);
//        request.setUsername(username);
//    }
// }
