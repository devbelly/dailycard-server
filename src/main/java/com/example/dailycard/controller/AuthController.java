package com.example.dailycard.controller;

import com.example.dailycard.dto.CreateUserRequest;
import com.example.dailycard.dto.LoginRequest;
import com.example.dailycard.dto.UserView;
import com.example.dailycard.mapper.UserViewMapper;
import com.example.dailycard.repository.UserRepository;
import com.example.dailycard.security.JwtTokenProvider;
import com.example.dailycard.security.UserPrincipal;
import com.example.dailycard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserViewMapper userViewMapper;
    private final UserService userService;

    @PostMapping("/login")
    public UserView login(@RequestBody @Valid LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsernameOrEmail(), request.getPassword()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String token = jwtTokenProvider.generateAccessToken(userPrincipal);

        UserView userView = userViewMapper.toUserView(userPrincipal);
        userView.setToken(token);
        return userView;
    }

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody @Validated CreateUserRequest request)
            throws Exception {

        return ResponseEntity.ok(userService.create(request));
    }
}
