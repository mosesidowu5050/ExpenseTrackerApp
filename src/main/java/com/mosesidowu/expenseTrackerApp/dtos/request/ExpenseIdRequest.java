package com.mosesidowu.expenseTrackerApp.dtos.request;

import lombok.Data;

@Data
public class ExpenseIdRequest {

    private String expenseId;
    private String userId;
    private String currencyCode;
}
