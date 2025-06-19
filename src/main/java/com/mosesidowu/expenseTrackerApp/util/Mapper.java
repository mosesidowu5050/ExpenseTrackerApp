package com.mosesidowu.expenseTrackerApp.util;

import com.mosesidowu.expenseTrackerApp.data.models.Expense;
import com.mosesidowu.expenseTrackerApp.data.models.User;
import com.mosesidowu.expenseTrackerApp.dtos.request.ExpenseRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.RegisterUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.UpdateExpenseRequest;
import com.mosesidowu.expenseTrackerApp.dtos.response.ExpenseResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.LoginUserResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.RegisterUserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Mapper {

    public static User userMapper(RegisterUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    public static RegisterUserResponse userMapperResponse(User user) {
        RegisterUserResponse response = new RegisterUserResponse();
        response.setId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setMessage("Registration successful");

        return response;
    }

    public static LoginUserResponse userMapper(User user) {
        LoginUserResponse response = new LoginUserResponse();
        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setMessage("Login successfully");

        return response;
    }


    public static Expense toExpense(ExpenseRequest request) {
        Expense expense = new Expense();
        expense.setUserId(request.getUserId());
        expense.setExpenseTitle(request.getExpenseTitle());
        expense.setExpenseDescription(request.getExpenseDescription());
        expense.setExpenseAmount(request.getExpenseAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());

        return expense;
    }

    public static ExpenseResponse toExpenseResponse(Expense expense, String currencyCode) {
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getExpenseId());
        response.setTitle(expense.getExpenseTitle());
        response.setDescription(expense.getExpenseDescription());
        response.setFormattedAmount(Helper.formatAmountWithCurrency(expense.getExpenseAmount(), currencyCode));
        response.setCategory(expense.getCategory());
        response.setCreatedAt(expense.getExpenseDate());
        response.setExpenseAmount(expense.getExpenseAmount());

        return response;
    }

    public static ExpenseResponse toExpense(Expense expense) {
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getExpenseId());
        response.setTitle(expense.getExpenseTitle());
        response.setDescription(expense.getExpenseDescription());
        response.setCategory(expense.getCategory());
        response.setCreatedAt(expense.getExpenseDate());
        response.setExpenseAmount(expense.getExpenseAmount());
        response.setFormattedAmount(Helper.formatAmountWithAmount(expense.getExpenseAmount()));

        return response;
    }

    public static Expense getExpense(UpdateExpenseRequest request, Expense expense) {
        expense.setExpenseTitle(request.getExpenseTitle());
        expense.setExpenseDescription(request.getExpenseDescription());
        expense.setExpenseAmount(request.getExpenseAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());

        return expense;
    }


    public static List<ExpenseResponse> getExpenseResponses(List<Expense> expenses) {
        List<ExpenseResponse> responses = new ArrayList<>();
        for (Expense expense : expenses) {
            ExpenseResponse response = new ExpenseResponse();
            response.setId(expense.getExpenseId());
            response.setTitle(expense.getExpenseTitle());
            response.setCategory(expense.getCategory());
            response.setExpenseAmount(expense.getExpenseAmount());
            response.setCreatedAt(expense.getExpenseDate());
            response.setDescription(expense.getExpenseDescription());
            response.setFormattedAmount(Helper.formatAmountWithAmount(expense.getExpenseAmount()));

            responses.add(response);
        }

        return responses;
    }
}
