package com.mosesidowu.expenseTrackerApp.controller;

import com.mosesidowu.expenseTrackerApp.dtos.request.LogingUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.RegisterUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.response.ApiResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.LoginUserResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.RegisterUserResponse;
import com.mosesidowu.expenseTrackerApp.exception.UserException;
import com.mosesidowu.expenseTrackerApp.services.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            RegisterUserResponse response = userService.register(registerUserRequest);
            return new ResponseEntity<>(new ApiResponse(response, true), CREATED);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), BAD_REQUEST);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogingUserRequest logingUserRequest) {
        try {
            LoginUserResponse response = userService.login(logingUserRequest);
            return new ResponseEntity<>(new ApiResponse(response, true), OK);

        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), BAD_REQUEST);
        }
    }
}
