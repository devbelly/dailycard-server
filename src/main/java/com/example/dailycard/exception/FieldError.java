package com.example.dailycard.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FieldError {
    private String field;
    private String value;
    private String reason;
}
