package com.mosesidowu.expenseTrackerApp.data.repository;


import com.mosesidowu.expenseTrackerApp.data.models.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpensesRepository extends MongoRepository<Expense,String> {

    List<Expense> findByUserId(String userId);

    Optional<Expense> findByExpenseIdAndUserId(String expenseId, String userId);

    List<Expense> findByUserIdAndExpenseTitleContainingIgnoreCase(String userId, String expenseTitle);

    List<Expense> findByUserIdAndCategory(String userId, String category);

    List<Expense> findByUserIdAndExpenseDateBetween(String userId, Date start, Date end);

    List<Expense> findExpenseByExpenseTitle(String groceries);
    Optional<Expense> findExpenseByExpenseId(String nonExistent);
}
