package com.mosesidowu.expenseTrackerApp.data.repository;

import com.mosesidowu.expenseTrackerApp.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findUserByPassword(String password);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
