package com.mosesidowu.expenseTrackerApp.dtos.response;

import lombok.Data;

@Data
public class TotalExpenseResponse {

    private String totalAmount;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
