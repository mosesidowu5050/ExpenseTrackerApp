package com.mosesidowu.expenseTrackerApp.dtos.request;

import lombok.Data;

@Data
public class TotalExpenseRequest {

    private String userId;
    private String currencyCode;
}
