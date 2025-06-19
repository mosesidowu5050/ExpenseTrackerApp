package com.mosesidowu.expenseTrackerApp.util;

import com.mosesidowu.expenseTrackerApp.data.models.Expense;
import com.mosesidowu.expenseTrackerApp.data.models.User;
import com.mosesidowu.expenseTrackerApp.dtos.request.ExpenseRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.RegisterUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.UpdateExpenseRequest;
import com.mosesidowu.expenseTrackerApp.dtos.response.ExpenseResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.LoginUserResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.RegisterUserResponse;
import com.mosesidowu.expenseTrackerApp.exception.ExpenseException;
import com.mosesidowu.expenseTrackerApp.exception.UserException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        validateExpenseRequest(request);

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
        validateExpenseRequest(expense);

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
        validateUpdateExpenseRequest(request);

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



    private static void validateExpenseRequest(Expense request) {
        isValidTitle(request.getExpenseTitle());
        isValidDescription(request.getExpenseDescription());
        isValidAmount(request.getExpenseAmount());
        isValidCategory(request.getCategory());
        isValidDate(request.getExpenseDate());
        isValidId(request.getExpenseId());
        isValidId(request.getUserId());
    }


    private static void validateUpdateExpenseRequest(UpdateExpenseRequest request) {
        isValidTitle(request.getExpenseTitle());
        isValidDescription(request.getExpenseDescription());
        isValidAmount(request.getExpenseAmount());
        isValidCategory(request.getCategory());
        isValidAmount(request.getExpenseAmount());
        isValidDate(request.getExpenseDate());
        isValidId(request.getExpenseId());
    }



    private static void validateExpenseRequest(ExpenseRequest request) {
        isValidTitle(request.getExpenseTitle());
        isValidDescription(request.getExpenseDescription());
        isValidAmount(request.getExpenseAmount());
        isValidCategory(request.getCategory());
        isValidDate(request.getExpenseDate());
        isValidId(request.getUserId());
    }

    private static void isValidId(String id){
        boolean isNotValidId = id == null || id.isEmpty();
        if (isNotValidId) throw new UserException("Invalid id.");
    }

    private static void isValidTitle(String title) {
        boolean isNotValidExpenseTitle = title == null || title.isEmpty();
        if (isNotValidExpenseTitle) throw new ExpenseException("Expense Title cannot be empty");
    }

    private static void isValidDate(LocalDate date) {
        boolean isNotValidExpenseDate = date == null;
        if (isNotValidExpenseDate) throw new ExpenseException("Expense Date cannot be empty");
    }


    private static void isValidCategory(String category) {
        boolean isNotValidCategory = category == null || category.isEmpty();
        if (isNotValidCategory) throw new ExpenseException("Category cannot be empty");
    }


    private static void isValidAmount(double amount) {
        boolean isNotValidExpenseAmount = amount <= 0;
        if (isNotValidExpenseAmount) throw new ExpenseException("Enter valid amount");
    }

    private static void isValidDescription(String description) {
        boolean  isNotValidExpenseDescription = description == null || description.isEmpty();
        if (isNotValidExpenseDescription) throw new ExpenseException("Expense Description cannot be empty");
    }

}
