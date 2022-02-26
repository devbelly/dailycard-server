package com.example.dailycard.controller;

import com.example.dailycard.dto.FieldRequest;
import com.example.dailycard.dto.FieldView;
import com.example.dailycard.dto.ListResponse;
import com.example.dailycard.security.UserPrincipal;
import com.example.dailycard.service.FieldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/field")
@RequiredArgsConstructor
@Slf4j
public class FieldController {

    private final FieldService fieldService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public FieldView create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody FieldRequest fieldRequest) {

        return fieldService.create(userPrincipal, fieldRequest);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ListResponse<FieldView> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ListResponse<>(fieldService.getAllField(userPrincipal));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public FieldView getOne(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {

        return fieldService.getOneField(userPrincipal, id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public FieldView edit(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long id,
            @RequestBody FieldRequest fieldRequest) {
        return fieldService.update(userPrincipal, fieldRequest, id);
    }
}
