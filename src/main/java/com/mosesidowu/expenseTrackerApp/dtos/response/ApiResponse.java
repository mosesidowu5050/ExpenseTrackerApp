package com.mosesidowu.expenseTrackerApp.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private Object data;
    private boolean success;

}
