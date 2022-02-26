package com.example.dailycard.mapper;

import com.example.dailycard.model.Tag;
import com.example.dailycard.repository.TagRepository;
import com.example.dailycard.security.UserPrincipal;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

@Mapper(componentModel = "spring")
public class StringTagMapper {

    @Autowired private TagRepository tagRepository;

    public String tagToString(Tag tag) {
        return tag.getName();
    }

    public Tag stringToTag(String name) {
        UserPrincipal userPrincipal =
                (UserPrincipal)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return tagRepository.findByCreatedByAndName(userPrincipal.getUsername(), name);
    }
}
