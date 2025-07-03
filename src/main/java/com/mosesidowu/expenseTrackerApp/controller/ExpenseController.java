package com.mosesidowu.expenseTrackerApp.controller;

import com.mosesidowu.expenseTrackerApp.dtos.request.*;
import com.mosesidowu.expenseTrackerApp.dtos.response.ApiResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.ExpenseResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.TotalExpenseResponse;
import com.mosesidowu.expenseTrackerApp.exception.UserException;
import com.mosesidowu.expenseTrackerApp.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody ExpenseRequest request) {
        try {
            ExpenseResponse response = expenseService.addExpense(request);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateExpense(@RequestBody UpdateExpenseRequest request) {
        try {
            ExpenseResponse response = expenseService.updateExpense(request);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable("expenseId") String expenseId, @RequestParam("userId") String userId) {
        try {
            expenseService.deleteExpense(expenseId, userId);
            return new ResponseEntity<>(new ApiResponse("Deleted successfully", true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses(@RequestParam("userId") String userId) {
        try {
            List<ExpenseResponse> responses = expenseService.getAllExpenses(userId);
            return new ResponseEntity<>(new ApiResponse(responses, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchExpensesByTitle(@RequestParam("userId") String userId,
                                                   @RequestParam("title") String title)  {
        try {
            List<ExpenseResponse> responses = expenseService.searchExpensesByTitle(userId, title);
            return new ResponseEntity<>(new ApiResponse(responses, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/filter/category")
    public ResponseEntity<?> filterByCategory(@RequestParam("userId") String userId,
                                              @RequestParam("category") String category) {
        try {
            List<ExpenseResponse> responses = expenseService.filterByCategory(userId, category);
            return new ResponseEntity<>(new ApiResponse(responses, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/filter/date-range")
    public ResponseEntity<?> filterByDateRange(
            @RequestParam("userId") String userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        try {
            List<ExpenseResponse> responses = expenseService.filterByDateRange(userId, startDate, endDate);
            return new ResponseEntity<>(new ApiResponse(responses, true), HttpStatus.OK);
        }
        catch (UserException e){
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/total")
    public ResponseEntity<?> getTotalExpense(@RequestParam("userId") String userId) {
        try {
            TotalExpenseResponse response = expenseService.getTotalExpense(userId);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}
