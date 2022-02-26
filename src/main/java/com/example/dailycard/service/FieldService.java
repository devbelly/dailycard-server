package com.example.dailycard.service;

import com.example.dailycard.dto.FieldRequest;
import com.example.dailycard.dto.FieldView;
import com.example.dailycard.mapper.FieldRequestMapper;
import com.example.dailycard.mapper.FieldViewMapper;
import com.example.dailycard.model.*;
import com.example.dailycard.repository.*;
import com.example.dailycard.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FieldService {

    private final FieldRepository fieldRepository;
    private final FieldRequestMapper fieldRequestMapper;
    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;
    private final FieldViewMapper fieldViewMapper;
    private final TagRepository tagRepository;
    private final CardRepository cardRepository;

    @Transactional
    public FieldView create(UserPrincipal userPrincipal, FieldRequest fieldRequest) {
        String username = userPrincipal.getUsername();
        Long id = fieldRequest.getTemplateNumber();

        Field field = fieldRequestMapper.create(fieldRequest);

        Template template = templateRepository.findByCreatedByAndId(username, id);
        field.setTemplate(template);

        Tag tag = tagRepository.findByCreatedByAndName(username, fieldRequest.getTag());
        field.setTag(tag);

        List<Card> cards = template.createCards(field);
        for (Card card : cards) {
            card.setField(field);
            card.setTag(tag);
            card.getBack().setTag(tag);
        }

        field.setCards(cards);
        Field result = fieldRepository.save(field);

        return fieldViewMapper.toFieldView(result);
    }

    @Transactional
    public FieldView update(UserPrincipal userPrincipal, FieldRequest fieldRequest, Long id) {

        String username = userPrincipal.getUsername();

        Field field = fieldRepository.findByCreatedByAndId(username, id);

        fieldRequestMapper.update(fieldRequest, field);

        for (Card card : field.getCards()) {
            if (card.getWord() != null) card.setWord(field.getWord());
            if (card.getMean() != null) card.setMean(field.getMean());
            cardRepository.save(card);
        }

        return fieldViewMapper.toFieldView(fieldRepository.save(field));
    }

    @Transactional
    public List<FieldView> getAllField(UserPrincipal userPrincipal) {
        String username = userPrincipal.getUsername();

        List<Field> fields = fieldRepository.findByCreatedBy(username);

        return fieldViewMapper.toFieldView(fields);
    }

    @Transactional
    public FieldView getOneField(UserPrincipal userPrincipal, Long id) {
        String username = userPrincipal.getUsername();
        Field field = fieldRepository.findByCreatedByAndId(username, id);
        return fieldViewMapper.toFieldView(field);
    }
}
