package com.example.dailycard.service;

import com.example.dailycard.dto.ChoiceRequest;
import com.example.dailycard.dto.ChoiceView;
import com.example.dailycard.mapper.ChoiceRequestMapper;
import com.example.dailycard.mapper.ChoiceViewMapper;
import com.example.dailycard.model.Choice;
import com.example.dailycard.model.Template;
import com.example.dailycard.repository.ChoiceRepository;
import com.example.dailycard.repository.TemplateRepository;
import com.example.dailycard.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final ChoiceViewMapper choiceViewMapper;
    private final TemplateRepository templateRepository;
    private final ChoiceRequestMapper choiceRequestMapper;

    @Transactional
    public List<ChoiceView> getAll(UserPrincipal userPrincipal, Long id) {

        String username = userPrincipal.getUsername();
        List<Choice> choices =
                choiceRepository.findAllByCreatedByAndTemplateIdAndBackIsNotNull(username, id);

        return choiceViewMapper.toChoiceView(choices);
    }

    @Transactional
    public List<ChoiceView> create(
            UserPrincipal userPrincipal, ChoiceRequest choiceRequest, Long id) {
        String username = userPrincipal.getUsername();

        Template template = templateRepository.findByCreatedByAndId(username, id);
        Choice newChoice = choiceRequestMapper.create(choiceRequest);
        newChoice.setTemplate(template);
        template.getChoices().add(newChoice);

        templateRepository.save(template);

        return getAll(userPrincipal, id);
    }

    @Transactional
    public List<ChoiceView> delete(UserPrincipal userPrincipal, Long id) {
        choiceRepository.deleteById(id);
        return getAll(userPrincipal, id);
    }

    @Transactional
    public ChoiceView edit(UserPrincipal userPrincipal, ChoiceRequest choiceRequest, Long id) {

        String username = userPrincipal.getUsername();

        Choice choice = choiceRepository.findByCreatedByAndId(username, id);

        choiceRequestMapper.update(choiceRequest, choice);

        return choiceViewMapper.toChoiceView(choiceRepository.save(choice));
    }

    @Transactional
    public ChoiceView getOne(UserPrincipal userPrincipal, Long id) {
        String username = userPrincipal.getUsername();

        Choice choice = choiceRepository.findByCreatedByAndId(username, id);

        return choiceViewMapper.toChoiceView(choice);
    }
}
