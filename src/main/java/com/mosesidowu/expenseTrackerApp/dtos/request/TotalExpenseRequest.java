package com.mosesidowu.expenseTrackerApp.dtos.request;

import lombok.Data;

@Data
public class TotalExpenseRequest {

    private String userId;
    private String currencyCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
