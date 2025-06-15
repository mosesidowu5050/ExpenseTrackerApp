package com.mosesidowu.expenseTrackerApp.dtos.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExpenseResponse {

        private String id;
        private String title;
        private String description;
        private String category;
        private double expenseAmount;
        private String formattedAmount;
        private LocalDate createdAt;

}
