package com.beltexam.beltexam.validator;

import com.beltexam.beltexam.models.User;
import com.beltexam.beltexam.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    public UserValidator (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean supports (Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate (Object target, Errors errors) {
        User user = (User) target;
        List<User> allusers = userRepository.findAll();


        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "PasswordConfirm");
        }
        for (int i = 0; i < allusers.size(); i++) {
            String email = allusers.get(i).getEmail();
            if (email.equals(user.getEmail())) {
                errors.rejectValue("email", "EmailIsAlreadyThere");
            }
        }
    }
}