package com.mosesidowu.expenseTrackerApp.data.repository;

import com.mosesidowu.expenseTrackerApp.data.models.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExpensesRepositoryTest {

    @Autowired
    private ExpensesRepository expensesRepository;

    private Expense testExpense;

    @BeforeEach
    void setUp() {
        expensesRepository.deleteAll();
    }

    @Test
    @DisplayName("save expenses")
    void testSaveExpenses_countIsOne() {
        testExpense = saveExpense();
        expensesRepository.save(testExpense);
    }

    @Test
    @DisplayName("Should find expense by expenseId")
    void testFindExpenseByExpenseId() {
        testExpense = saveExpense();
        expensesRepository.save(testExpense);

        Optional<Expense> result = expensesRepository.findExpenseByExpenseId("EXP001");
        assertTrue(result.isPresent());
        assertEquals("Groceries", result.get().getExpenseTitle());
    }

    @Test
    @DisplayName("Should find expenses by expenseTitle")
    void testFindExpenseByExpenseTitle() {
        testExpense = saveExpense();
        expensesRepository.save(testExpense);

        List<Expense> results = expensesRepository.findExpenseByExpenseTitle("Groceries");
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("user123", results.get(0).getUserId());
    }

    @Test
    @DisplayName("Should return empty when expenseId does not exist")
    void testFindExpenseByNonExistingId() {
        testExpense = saveExpense();
        expensesRepository.save(testExpense);

        Optional<Expense> result = expensesRepository.findExpenseByExpenseId("NON_EXISTENT");
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should find expenses by userId")
    void testFindByUserId() {
        testExpense = saveExpense();
        expensesRepository.save(testExpense);

        List<Expense> results = expensesRepository.findByUserId("user123");
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("Groceries", results.get(0).getExpenseTitle());
    }

    @Test
    @DisplayName("Should search expenses by userId and partial title")
    void testFindByUserIdAndTitleContaining() {
        testExpense = saveExpense();
        expensesRepository.save(testExpense);

        List<Expense> results = expensesRepository.findByUserIdAndExpenseTitleContainingIgnoreCase("user123", "groc");
        assertFalse(results.isEmpty());
        assertEquals("Groceries", results.get(0).getExpenseTitle());
    }


    private Expense saveExpense(){
        testExpense = new Expense();
        testExpense.setExpenseId("EXP001");
        testExpense.setUserId("user123");
        testExpense.setExpenseTitle("Groceries");
        testExpense.setExpenseDescription("Weekly grocery shopping");
        testExpense.setExpenseAmount(120.50);

        return testExpense;
    }
}
