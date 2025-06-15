package com.mosesidowu.expenseTrackerApp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document
public class Expense {

    @Id
    private String expenseId;
    private String userId;
    private String expenseTitle;
    private String expenseDescription;
    private double expenseAmount;
    private String category;
    private LocalDate expenseDate;

}
