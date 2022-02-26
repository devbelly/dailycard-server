package com.example.dailycard.service;

import com.example.dailycard.dto.CardView;
import com.example.dailycard.mapper.CardViewMapper;
import com.example.dailycard.model.Card;
import com.example.dailycard.model.Tag;
import com.example.dailycard.repository.CardRepository;
import com.example.dailycard.repository.TagRepository;
import com.example.dailycard.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final TagRepository tagRepository;
    private final CardViewMapper cardViewMapper;

    @Transactional
    public List<CardView> getCardsByTagId(UserPrincipal userPrincipal, Long TagId) {
        String username = userPrincipal.getUsername();

        Tag tag = tagRepository.findByCreatedByAndId(username, TagId);

        List<Card> cards = cardRepository.findByCreatedByAndTagAndBackIsNotNull(username, tag);

        return cardViewMapper.toCardView(cards);
    }
}
