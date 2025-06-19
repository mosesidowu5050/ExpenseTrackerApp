package com.mosesidowu.expenseTrackerApp.util;

import com.mosesidowu.expenseTrackerApp.data.models.Expense;
import com.mosesidowu.expenseTrackerApp.data.repository.ExpensesRepository;
import com.mosesidowu.expenseTrackerApp.dtos.request.DeleteExpenseRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.ExpenseRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.UpdateExpenseRequest;
import com.mosesidowu.expenseTrackerApp.exception.ExpenseException;
import com.mosesidowu.expenseTrackerApp.exception.UserException;

import java.util.*;

public class Helper {


    public static Expense getExpenseByIdAndUserId(ExpensesRepository repository, String expenseId, String userId) {
        return repository.findByExpenseIdAndUserId(expenseId, userId)
                .orElseThrow(() -> new ExpenseException("Expense not found"));
    }

    public static double getTotalExpenseForUser(ExpensesRepository repository, String userId) {
        List<Expense> expenses = repository.findByUserId(userId);
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getExpenseAmount();
        }
        return total;
    }

    public static String formatAmountWithCurrency(double amount, String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            currencyCode = "USD";
        }
        return currencyCode.toUpperCase() + " " + String.format("%.2f", amount);
    }

    public static String formatAmountWithAmount(double amount) {
        return String.format("%.2f", amount);
    }

}
