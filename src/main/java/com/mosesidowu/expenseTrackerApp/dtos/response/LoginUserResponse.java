package com.mosesidowu.expenseTrackerApp.dtos.response;

import lombok.Data;

@Data
public class LoginUserResponse {

    private String userId;
    private String email;
    private String message;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
