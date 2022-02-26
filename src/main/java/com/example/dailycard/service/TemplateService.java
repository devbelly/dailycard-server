package com.example.dailycard.service;

import com.example.dailycard.dto.ListResponse;
import com.example.dailycard.dto.TemplateRequest;
import com.example.dailycard.dto.TemplateView;
import com.example.dailycard.mapper.TemplateRequestMapper;
import com.example.dailycard.mapper.TemplateViewMapper;
import com.example.dailycard.model.Template;
import com.example.dailycard.repository.TemplateRepository;
import com.example.dailycard.repository.UserRepository;
import com.example.dailycard.security.UserPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateRequestMapper templateRequestMapper;
    private final TemplateViewMapper templateViewMapper;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Transactional
    public List<TemplateView> create(UserPrincipal userPrincipal, TemplateRequest templateRequest)
            throws JsonProcessingException {
        String username = userPrincipal.getUsername();

        Template template = templateRequestMapper.create(templateRequest);

        templateRepository.save(template);
        List<Template> templates = templateRepository.findByCreatedBy(username);
        return templateViewMapper.toTemplateView(templates);
    }

    @Transactional
    public TemplateView update(
            UserPrincipal userPrincipal, Long id, TemplateRequest templateRequest) {

        String username = userPrincipal.getUsername();

        Template template = templateRepository.findByCreatedByAndId(username, id);

        templateRequestMapper.update(templateRequest, template);

        return templateViewMapper.toTemplateView(templateRepository.save(template));
    }

    /** 연관된 choice 날아가는 거 관리해야함. */
    @Transactional
    public TemplateView delete(UserPrincipal userPrincipal, Long id) {
        String username = userPrincipal.getUsername();

        Template template = templateRepository.findByCreatedByAndId(username, id);
        templateRepository.delete(template);

        return templateViewMapper.toTemplateView(template);
    }

    @Transactional
    public List<TemplateView> getAll(UserPrincipal userPrincipal) {
        String username = userPrincipal.getUsername();

        List<Template> templates = templateRepository.findByCreatedBy(username);
        return templateViewMapper.toTemplateView(templates);
    }

    @Transactional
    public TemplateView getOne(UserPrincipal userPrincipal, Long id) {
        String username = userPrincipal.getUsername();

        Template template = templateRepository.findByCreatedByAndId(username, id);

        return templateViewMapper.toTemplateView(template);
    }
}
