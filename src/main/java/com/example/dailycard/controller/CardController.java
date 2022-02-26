package com.example.dailycard.controller;

import com.example.dailycard.dto.CardView;
import com.example.dailycard.dto.ListResponse;
import com.example.dailycard.security.UserPrincipal;
import com.example.dailycard.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardService cardService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ListResponse<CardView> getCardsByTag(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
        return new ListResponse<>(cardService.getCardsByTagId(userPrincipal, id));
    }
}
