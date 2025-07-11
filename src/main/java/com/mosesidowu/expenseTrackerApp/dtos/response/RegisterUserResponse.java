package com.mosesidowu.expenseTrackerApp.dtos.response;

import lombok.Data;

@Data
public class RegisterUserResponse {

    private String id;
    private String username;
    private String email;
    private String message;


    public void setId(String id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
