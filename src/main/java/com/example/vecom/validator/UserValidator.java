package com.example.vecom.validator;

import com.example.vecom.model.User;
import com.example.vecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "NotEmpty");
        if(user.getFullname().length() < 6 || user.getFullname().length() > 32){
            errors.rejectValue("fullname", "Size.userForm.fullname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if(userService.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if(user.getPassword().length() < 8){
            errors.rejectValue("password", "Size.userForm.password");
        }

        if(!user.getPasswordConfirm().equals(user.getPassword())){
            errors.rejectValue("password", "Diff.userForm.passwordConfirm");
        }
    }
}
