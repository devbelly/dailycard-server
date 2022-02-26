package com.example.dailycard.controller;

import com.example.dailycard.dto.ChoiceView;
import com.example.dailycard.dto.ListResponse;
import com.example.dailycard.dto.TemplateRequest;
import com.example.dailycard.dto.TemplateView;
import com.example.dailycard.mapper.TemplateRequestMapper;
import com.example.dailycard.model.User;
import com.example.dailycard.security.UserPrincipal;
import com.example.dailycard.service.ChoiceService;
import com.example.dailycard.service.TemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
@Slf4j
public class TemplateController {

    private final TemplateService templateService;
    private final TemplateRequestMapper templateRequestMapper;
    private final ChoiceService choiceService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ListResponse<TemplateView> create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody TemplateRequest templateRequest)
            throws JsonProcessingException {

        return new ListResponse<>(templateService.create(userPrincipal, templateRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TemplateView> update(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long id,
            @RequestBody @Valid TemplateRequest req) {

        return ResponseEntity.ok(templateService.update(userPrincipal, id, req));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public TemplateView delete(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
        return templateService.delete(userPrincipal, id);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ListResponse<TemplateView> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        return new ListResponse<>(templateService.getAll(userPrincipal));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public TemplateView getTemplate(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {

        return templateService.getOne(userPrincipal, id);
    }
}
