package com.mosesidowu.expenseTrackerApp.services;

import com.mosesidowu.expenseTrackerApp.data.models.Expense;
import com.mosesidowu.expenseTrackerApp.data.models.User;
import com.mosesidowu.expenseTrackerApp.data.repository.ExpensesRepository;
import com.mosesidowu.expenseTrackerApp.data.repository.UserRepository;
import com.mosesidowu.expenseTrackerApp.dtos.request.ExpenseRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.TotalExpenseRequest;
import com.mosesidowu.expenseTrackerApp.dtos.response.ExpenseResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.TotalExpenseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExpenseServiceImplTest {

        @Autowired
        private ExpenseService expenseService;
        @Autowired
        private ExpensesRepository expensesRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
        void setUp() {
            expensesRepository.deleteAll();
            userRepository.deleteAll();
        }


    @Test
    void testAddExpense() {
        User user = new User();
        user.setUsername("testUser123");
        user.setEmail("test@example.com");
        userRepository.save(user);
        String userId = user.getUserId();

        ExpenseRequest request = new ExpenseRequest();
        request.setUserId(userId);
        request.setExpenseTitle("Test Expense");
        request.setExpenseDescription("Test Description");
        request.setExpenseAmount(150.75);
        request.setCategory("Food");
        request.setExpenseDate(LocalDate.now());
        request.setCurrencyCode("USD");

        ExpenseResponse response = expenseService.addExpense(request);

        assertNotNull(response);
        assertEquals("Test Expense", response.getTitle());
        assertEquals("USD 150.75", response.getFormattedAmount());
        assertEquals("Food", response.getCategory());
    }

    @Test
    void testGetTotalExpense() {
        User user = new User();
        user.setUsername("testUser123");
        user.setEmail("test@example.com");
        userRepository.save(user);
        String userId = user.getUserId();


        Expense expense1 = new Expense();
        expense1.setUserId(userId);
        expense1.setExpenseTitle("Food Title");
        expense1.setExpenseDescription("Food Description");
        expense1.setExpenseAmount(150.00);
        expense1.setCategory("Food");
        expense1.setExpenseDate(LocalDate.now());
        expensesRepository.save(expense1);

        Expense expense2 = new Expense();
        expense2.setUserId(userId);
        expense2.setExpenseTitle("New Title");
        expense2.setExpenseDescription("New Description");
        expense2.setExpenseAmount(150.00);
        expense2.setCategory("Food");
        expense2.setExpenseDate(LocalDate.now());
        expensesRepository.save(expense2);

        TotalExpenseRequest request = new TotalExpenseRequest();
        request.setUserId(userId);
        request.setCurrencyCode("USD");

        TotalExpenseResponse response = expenseService.getTotalExpense(request.getUserId());
        assertEquals("USD 300.00", response.getTotalAmount());
    }


}
