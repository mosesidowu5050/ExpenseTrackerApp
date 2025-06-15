package com.mosesidowu.expenseTrackerApp.services;

import com.mosesidowu.expenseTrackerApp.dtos.request.*;
import com.mosesidowu.expenseTrackerApp.dtos.response.ExpenseResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.TotalExpenseResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseService {

    ExpenseResponse addExpense(ExpenseRequest request);

    ExpenseResponse updateExpense(UpdateExpenseRequest request);

    ExpenseResponse getExpenseById(ExpenseIdRequest request);

    public List<ExpenseResponse> getAllExpenses(String userId);

    List<ExpenseResponse> searchExpensesByTitle(SearchExpenseByTitleRequest request);

    List<ExpenseResponse> filterByCategory(FilterByCategoryRequest request);

    List<ExpenseResponse> filterByDateRange(FilterByDateRangeRequest request);

    TotalExpenseResponse getTotalExpense(String userId);

    void deleteExpense(String expenseId, String userId);
}


