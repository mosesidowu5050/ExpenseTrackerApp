package com.mosesidowu.expenseTrackerApp.services;

import com.mosesidowu.expenseTrackerApp.data.repository.UserRepository;
import com.mosesidowu.expenseTrackerApp.dtos.request.LogingUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.RegisterUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.response.LoginUserResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.RegisterUserResponse;
import com.mosesidowu.expenseTrackerApp.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    private RegisterUserRequest registerUserRequest = new RegisterUserRequest();
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse();
    private LogingUserRequest logingUserRequest = new LogingUserRequest();
    private LoginUserResponse loginUserResponse = new LoginUserResponse();

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void registerOneUser_countIsOneTest(){
        registerUserResponse = userService.register(registerUserRequest());
        assertNotNull(registerUserResponse);
        assertEquals(1, userRepository.count());
    }

    @Test
    void loginUser_returnLoginSuccessfulTest(){
        userService.register(registerUserRequest());

        logingUserRequest.setEmail("claudi122@gmail.com");
        logingUserRequest.setPassword("1234");
        loginUserResponse = userService.login(logingUserRequest);
        assertNotNull(loginUserResponse);
        assertEquals("Login successfully",  loginUserResponse.getMessage());
    }

    @Test
    void loginUserWithInvalidDetails_throwExceptionTest(){
        logingUserRequest.setEmail("claudius122@email.com");
        logingUserRequest.setPassword("1234");
        assertThrows(UserException.class,() -> userService.login(logingUserRequest));
    }

    private RegisterUserRequest registerUserRequest(){
        registerUserRequest.setUsername("claudius122");
        registerUserRequest.setEmail("claudi122@gmail.com");
        registerUserRequest.setPassword("1234");

        return registerUserRequest;
    }
}
