package com.example.dailycard.exception;

import com.example.dailycard.dto.CreateUserRequest;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordMatch, CreateUserRequest> {

    @Override
    public void initialize(PasswordMatch p) {}

    public boolean isValid(CreateUserRequest request, ConstraintValidatorContext c) {
        String plainPassword = request.getPassword();
        String repeatPassword = request.getRePassword();

        if (!plainPassword.equals(repeatPassword)) {
            addConstraintViolation(c, String.format("일치하지 않습니다"));
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String msg) {
        // 기본 메시지 비활성화
        context.disableDefaultConstraintViolation();
        // 새로운 메시지 추가
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    }
}
