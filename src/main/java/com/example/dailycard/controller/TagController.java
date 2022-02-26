package com.example.dailycard.controller;

import com.example.dailycard.dto.ListResponse;
import com.example.dailycard.dto.TagRequest;
import com.example.dailycard.dto.TagView;
import com.example.dailycard.security.UserPrincipal;
import com.example.dailycard.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
@Slf4j
public class TagController {
    private final TagService tagService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public ListResponse<TagView> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ListResponse<>(tagService.getAll(userPrincipal));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ListResponse<TagView> createTag(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody TagRequest tagRequest) {

        return new ListResponse<>(tagService.create(userPrincipal, tagRequest));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public TagView getOne(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
        return tagService.getOne(userPrincipal, id);
    }

    //    @PutMapping
    //    @PreAuthorize("hasRole('USER')")
    //    public TagView edit(@AuthenticationPrincipal UserPrincipal userPrincipal,@RequestBody
    // TagRequest tagRequest){
    //        return
    //    }
}
