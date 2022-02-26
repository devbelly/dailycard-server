package com.example.dailycard.controller;

import com.example.dailycard.dto.UserView;
import com.example.dailycard.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserView getCurrentUser(
            HttpServletRequest request, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1].trim();
        UserView userView = new UserView();
        userView.setUsername(userPrincipal.getUsername());
        userView.setId(userPrincipal.getId());
        userView.setToken(token);
        return userView;
    }
}
