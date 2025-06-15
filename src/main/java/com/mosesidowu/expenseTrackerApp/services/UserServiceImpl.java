package com.mosesidowu.expenseTrackerApp.services;

import com.mosesidowu.expenseTrackerApp.data.models.User;
import com.mosesidowu.expenseTrackerApp.data.repository.UserRepository;
import com.mosesidowu.expenseTrackerApp.dtos.request.LogingUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.request.RegisterUserRequest;
import com.mosesidowu.expenseTrackerApp.dtos.response.LoginUserResponse;
import com.mosesidowu.expenseTrackerApp.dtos.response.RegisterUserResponse;
import com.mosesidowu.expenseTrackerApp.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import static com.mosesidowu.expenseTrackerApp.util.Mapper.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        validateUsername(registerUserRequest.getUsername());
        validateEmail(registerUserRequest.getEmail());
        validateEmailPattern(registerUserRequest.getEmail());

        User user = userMapper(registerUserRequest);
        userRepository.save(user);
        return userMapperResponse(user);
    }

    @Override
    public LoginUserResponse login(LogingUserRequest logingUserRequest) {
        User user = userRepository.findUserByEmail(logingUserRequest.getEmail())
                .orElseThrow(() -> new UserException("Email doesn't match"));
        validatePassword(logingUserRequest.getPassword());

        return userMapper(user);
    }


    private void validatePassword(String password) {
        userRepository.findUserByPassword(password).orElseThrow(() -> new UserException("Invalid Password"));
    }

    private void validateUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (!username.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$")) {
            throw new UserException("Username must be at least 5 characters and contain both letters and numbers.");
        }
        if (user.isPresent()) {
            throw new UserException("Username already exists.");
        }
    }

    private void validateEmail(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UserException("Email already exists.");
        }
    }

    private void validateEmailPattern(String email) {
        boolean isValidEmail = email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        if (!isValidEmail) throw new UserException("Invalid email format.");
    }
}
