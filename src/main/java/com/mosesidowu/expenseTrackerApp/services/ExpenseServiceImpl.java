package com.mosesidowu.expenseTrackerApp.services;

import com.mosesidowu.expenseTrackerApp.data.models.Expense;
import com.mosesidowu.expenseTrackerApp.data.repository.ExpensesRepository;
import com.mosesidowu.expenseTrackerApp.data.repository.UserRepository;
import com.mosesidowu.expenseTrackerApp.dtos.request.*;
import com.mosesidowu.expenseTrackerApp.dtos.response.ExpenseResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.TotalExpenseResponse;
import com.mosesidowu.expenseTrackerApp.exception.UserException;
import com.mosesidowu.expenseTrackerApp.exception.UserNotFoundException;
import com.mosesidowu.expenseTrackerApp.util.Helper;
import com.mosesidowu.expenseTrackerApp.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpensesRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Override
    public ExpenseResponse addExpense(ExpenseRequest request) {
        Helper.validateCreateRequest(request);
        Expense expense = Mapper.toExpense(request);
        Expense saved = expenseRepository.save(expense);

        return Mapper.toExpenseResponse(saved, request.getCurrencyCode());
    }

    @Override
    public ExpenseResponse updateExpense(UpdateExpenseRequest request) {
        Helper.validateUpdateRequest(request);

        Expense expense = Helper.getExpenseByIdAndUserId(expenseRepository, request.getExpenseId(), request.getUserId());

        expense.setExpenseTitle(request.getExpenseTitle());
        expense.setExpenseDescription(request.getExpenseDescription());
        expense.setExpenseAmount(request.getExpenseAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());
        Expense updated = expenseRepository.save(expense);

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
    public List<ExpenseResponse> searchExpensesByTitle(SearchExpenseByTitleRequest request) {
        List<Expense> expenses = expenseRepository.findByUserIdAndExpenseTitleContainingIgnoreCase(request.getUserId(), request.getTitle());
        List<ExpenseResponse> responses = new ArrayList<>();

        for (Expense exp : expenses) {
            responses.add(Mapper.toExpenseResponse(exp, request.getCurrencyCode()));
        }

        return responses;
    }

    @Override
    public List<ExpenseResponse> filterByCategory(FilterByCategoryRequest request) {
        List<Expense> expenses = expenseRepository.findByUserIdAndCategory(request.getUserId(), request.getCategory());
        List<ExpenseResponse> responses = new ArrayList<>();

        for (Expense exp : expenses) {
            responses.add(Mapper.toExpenseResponse(exp, request.getCurrencyCode()));
        }

        return responses;
    }

    @Override
    public List<ExpenseResponse> filterByDateRange(FilterByDateRangeRequest request) {
        List<Expense> expenses = expenseRepository.findByUserIdAndExpenseDateBetween(
                request.getUserId(), request.getStartDate(), request.getEndDate());

        List<ExpenseResponse> responses = new ArrayList<>();
        for (Expense exp : expenses) {
            responses.add(Mapper.toExpenseResponse(exp, request.getCurrencyCode()));
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



//    private String generateUniqueId() {
//        char[] characters = {
//                '1','2','3','4','5','6','7','8','9','0',
//                'a','b','c','d','e','f','g','h','i','j',
//                'k','l','m','n','o','p','q','r','s','t',
//                'u','v','w','x','y','z'
//        };
//
//        Random random = new Random();
//        String savedId;
//
//        do {
//            savedId = "";
//            for (int i = 0; i < 6; i++) {
//                int index = random.nextInt(characters.length);
//                savedId += characters[index];
//            }
//        } while (expenseRepository.findExpenseByExpenseId(savedId).isPresent());
//
//        return savedId.toUpperCase();
//    }

}
