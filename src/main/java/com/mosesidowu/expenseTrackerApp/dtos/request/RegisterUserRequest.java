package com.mosesidowu.expenseTrackerApp.dtos.request;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String username;
    private String email;
    private String password;

}
