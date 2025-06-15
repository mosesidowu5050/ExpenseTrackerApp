package com.mosesidowu.expenseTrackerApp.services;

import com.mosesidowu.expenseTrackerApp.data.models.User;
import com.mosesidowu.expenseTrackerApp.dtos.request.LogingUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.RegisterUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.response.LoginUserResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.RegisterUserResponse;
import org.springframework.stereotype.Component;


public interface UserService {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
    LoginUserResponse login(LogingUserRequest logingUserRequest);

}
