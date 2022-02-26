package com.example.dailycard.dto;

import com.example.dailycard.exception.PasswordMatch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@PasswordMatch()
public class CreateUserRequest {

    @NotBlank private String username;

    @NotBlank private String password;

    @NotBlank private String rePassword;

    private Set<String> authorities = new HashSet<>();

    public boolean checkPasswordValid() {
        if (password.equals(rePassword)) {
            return true;
        }
        return false;
    }
}
