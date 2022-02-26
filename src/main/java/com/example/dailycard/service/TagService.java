package com.example.dailycard.service;

import com.example.dailycard.dto.TagRequest;
import com.example.dailycard.dto.TagView;
import com.example.dailycard.mapper.TagRequestMapper;
import com.example.dailycard.mapper.TagViewMapper;
import com.example.dailycard.model.Tag;
import com.example.dailycard.repository.TagRepository;
import com.example.dailycard.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

    private final TagRepository tagRepository;
    private final TagViewMapper tagViewMapper;
    private final TagRequestMapper tagRequestMapper;

    @Transactional
    public List<TagView> getAll(UserPrincipal userPrincipal) {
        String username = userPrincipal.getUsername();

        List<Tag> tags = tagRepository.findAllByCreatedBy(username);

        List<TagView> tagViews = tagViewMapper.toTagView(tags);

        return tagViews;
    }

    @Transactional
    public List<TagView> create(UserPrincipal userPrincipal, TagRequest tagRequest) {
        String username = userPrincipal.getUsername();

        tagRepository.save(tagRequestMapper.toTag(tagRequest));
        List<Tag> tags = tagRepository.findAllByCreatedBy(username);

        return tagViewMapper.toTagView(tags);
    }

    @Transactional
    public TagView getOne(UserPrincipal userPrincipal, Long id) {
        String username = userPrincipal.getUsername();

        Tag tag = tagRepository.findByCreatedByAndId(username, id);
        return tagViewMapper.toTagView(tag);
    }
}
