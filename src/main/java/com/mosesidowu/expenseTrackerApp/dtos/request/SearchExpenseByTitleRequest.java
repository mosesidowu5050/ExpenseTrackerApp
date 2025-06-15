package com.mosesidowu.expenseTrackerApp.dtos.request;

import lombok.Data;

@Data
public class SearchExpenseByTitleRequest {

    private String userId;
    private String title;
    private String currencyCode;
}
