package com.mosesidowu.expenseTrackerApp.services;

import com.mosesidowu.expenseTrackerApp.data.models.Expense;
import com.mosesidowu.expenseTrackerApp.data.repository.ExpensesRepository;
import com.mosesidowu.expenseTrackerApp.dtos.request.*;
import com.mosesidowu.expenseTrackerApp.dtos.response.ExpenseResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.TotalExpenseResponse;
import com.mosesidowu.expenseTrackerApp.exception.UserException;
import com.mosesidowu.expenseTrackerApp.util.Helper;
import com.mosesidowu.expenseTrackerApp.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mosesidowu.expenseTrackerApp.util.Mapper.*;


@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpensesRepository expenseRepository;


    @Override
    public ExpenseResponse addExpense(ExpenseRequest request) {
//        Helper.validateCreateRequest(request);
        Expense expense = Mapper.toExpense(request);
        Expense saved = expenseRepository.save(expense);

        return Mapper.toExpenseResponse(saved, request.getCurrencyCode());
    }


    @Override
    public ExpenseResponse updateExpense(UpdateExpenseRequest request) {
//        Helper.validateUpdateRequest(request);

        Expense expense = Helper.getExpenseByIdAndUserId(expenseRepository, request.getExpenseId(), request.getUserId());
        Expense updated = getExpense(request, expense);
        expenseRepository.save(updated);

        return Mapper.toExpenseResponse(updated, request.getCurrencyCode());
    }



    @Override
    public void deleteExpense(String expenseId, String userId) {
        Expense expense = expenseRepository.findByExpenseIdAndUserId(expenseId, userId)
                .orElseThrow(() -> new UserException("Expense not found"));

        expenseRepository.delete(expense);
    }

    @Override
    public ExpenseResponse getExpenseById(ExpenseIdRequest request) {
        Expense expense = Helper.getExpenseByIdAndUserId(expenseRepository, request.getExpenseId(), request.getUserId());
        return Mapper.toExpenseResponse(expense, request.getCurrencyCode());
    }

    @Override
    public List<ExpenseResponse> getAllExpenses(String userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        List<ExpenseResponse> responses = new ArrayList<>();

        for (Expense exp : expenses) {
            responses.add(Mapper.toExpense(exp));
        }

        return responses;
    }


    @Override
    public List<ExpenseResponse> searchExpensesByTitle(String userId, String title) {
        List<Expense> expenses = expenseRepository.findByUserIdAndExpenseTitleContainingIgnoreCase(userId, title);
        List<ExpenseResponse> responses = new ArrayList<>();

        for (Expense exp : expenses) {
            responses.add(Mapper.toExpense(exp));
        }

        return responses;
    }


    @Override
    public List<ExpenseResponse> filterByCategory(String userId, String category) {
        List<Expense> expenses = expenseRepository.findByUserIdAndCategory(userId, category);
        List<ExpenseResponse> responses = new ArrayList<>();

        for (Expense exp : expenses) {
            responses.add(Mapper.toExpense(exp));
        }

        return responses;
    }


    @Override
    public TotalExpenseResponse getTotalExpense(String userId) {
        TotalExpenseRequest request = new TotalExpenseRequest();
        double total = Helper.getTotalExpenseForUser(expenseRepository, userId);
        String formatted = Helper.formatAmountWithCurrency(total, request.getCurrencyCode());

        TotalExpenseResponse response = new TotalExpenseResponse();
        response.setTotalAmount(formatted);
        return response;
    }


    public List<ExpenseResponse> filterByDateRange(String userId, LocalDate startDate, LocalDate endDate) {
        validateDate(startDate, endDate);

        Date from = Date.from(startDate.atStartOfDay(ZoneOffset.UTC).toInstant());
        Date to = Date.from(endDate.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant());
        List<Expense> expenses = expenseRepository.findByUserIdAndExpenseDateBetween(
                userId, from, to
        );

        return getExpenseResponses(expenses);
    }



    private static void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) throw new UserException("Start and end dates must not be null.");
        if (startDate.isAfter(endDate)) throw new UserException("Start date cannot be after end date.");
    }

}
