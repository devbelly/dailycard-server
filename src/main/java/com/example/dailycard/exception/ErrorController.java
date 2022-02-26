package com.example.dailycard.exception;

import com.example.dailycard.model.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    // handle for login request

    /**
     * ErrorResponse로 변경해야함
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialException(
            BadCredentialsException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = getFieldErrors(ex.getBindingResult());
        if (fieldErrors.size() == 0) {
            FieldError fieldError =
                    FieldError.builder().field("password").reason("password not match").build();
            fieldErrors.add(fieldError);
        }

        ErrorResponse errorResponse = buildFieldError(ErrorCode.INPUT_VALUE_INVALID, fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private List<FieldError> getFieldErrors(BindingResult bindingResult) {
        List<org.springframework.validation.FieldError> errors = bindingResult.getFieldErrors();

        return errors.parallelStream()
                .map(
                        error ->
                                FieldError.builder()
                                        .reason(error.getDefaultMessage())
                                        .field(error.getField())
                                        .value((String) error.getRejectedValue())
                                        .build())
                .collect(Collectors.toList());
    }

    private ErrorResponse buildFieldError(ErrorCode errorCode, List<FieldError> errors) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }
}
