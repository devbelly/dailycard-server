package com.example.dailycard.controller;

import com.example.dailycard.dto.*;
import com.example.dailycard.security.UserPrincipal;
import com.example.dailycard.service.ChoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/choice")
@RequiredArgsConstructor
@Slf4j
public class ChoiceController {

    private final ChoiceService choiceService;

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ListResponse<ChoiceView> create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody ChoiceRequest choiceRequest,
            @PathVariable Long id) {

        return new ListResponse<>(choiceService.create(userPrincipal, choiceRequest, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ListResponse<ChoiceView> delete(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
        return new ListResponse<>(choiceService.delete(userPrincipal, id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ChoiceView edit(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody ChoiceRequest choiceRequest,
            @PathVariable Long id) {

        return choiceService.edit(userPrincipal, choiceRequest, id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ChoiceView getOne(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
        return choiceService.getOne(userPrincipal, id);
    }
}
