package com.example.dailycard.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private String code;
    private int status;
    private List<FieldError>  errors = new ArrayList<>();


}
