package com.mosesidowu.expenseTrackerApp.services;

import com.mosesidowu.expenseTrackerApp.dtos.request.*;
import com.mosesidowu.expenseTrackerApp.dtos.response.ExpenseResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.TotalExpenseResponse;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

    ExpenseResponse addExpense(ExpenseRequest request);

    ExpenseResponse updateExpense(UpdateExpenseRequest request);

    ExpenseResponse getExpenseById(ExpenseIdRequest request);

    List<ExpenseResponse> getAllExpenses(String userId);

    List<ExpenseResponse> searchExpensesByTitle(String userId, String title);

    List<ExpenseResponse> filterByCategory(String userId, String category);

    List<ExpenseResponse> filterByDateRange(String userId, LocalDate startDate, LocalDate endDate);

    TotalExpenseResponse getTotalExpense(String userId);

    void deleteExpense(String expenseId, String userId);
}


