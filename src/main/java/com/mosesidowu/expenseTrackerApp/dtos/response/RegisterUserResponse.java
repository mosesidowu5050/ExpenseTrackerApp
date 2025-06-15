package com.mosesidowu.expenseTrackerApp.dtos.response;

import lombok.Data;

@Data
public class RegisterUserResponse {

    private String id;
    private String username;
    private String email;
    private String message;

}
