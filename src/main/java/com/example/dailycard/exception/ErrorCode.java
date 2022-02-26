package com.example.dailycard.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ACCOUNT_NOT_FOUND("AC_001","cannot found user",404),
    EMAIL_DUPLICATION("AC_001","email duplicated",400),
    INPUT_VALUE_INVALID("???","input is not valid",400);

    private final String code;
    private final String message;
    private final int status;
}
