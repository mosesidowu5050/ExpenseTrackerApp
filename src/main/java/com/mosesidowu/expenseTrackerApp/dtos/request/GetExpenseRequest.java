package com.mosesidowu.expenseTrackerApp.dtos.request;

import lombok.Data;

@Data
public class GetExpenseRequest {

    private String userId;
    private String expenseId;
    private String currencyCode;
}
