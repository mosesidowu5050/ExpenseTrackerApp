package com.mosesidowu.expenseTrackerApp.dtos.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilterByDateRangeRequest {

    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;

}
