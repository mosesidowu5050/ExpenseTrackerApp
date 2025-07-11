package com.mosesidowu.expenseTrackerApp.dtos.response;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
@RequiredArgsConstructor
public class ApiResponse {

    private Object data;
    private boolean success;

    public ApiResponse(Object data,  boolean success) {
        this.data = data;
        this.success = success;
    }

}
