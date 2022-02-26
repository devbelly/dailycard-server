package com.example.dailycard.mapper;

import com.example.dailycard.dto.CardView;
import com.example.dailycard.dto.ChoiceView;
import com.example.dailycard.dto.TagView;
import com.example.dailycard.model.Card;
import com.example.dailycard.model.Choice;
import com.example.dailycard.model.Tag;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CardViewMapper {

    @Mapping(target = "back", ignore = true)
    public abstract CardView toCardView(Card card);

    public abstract List<CardView> toCardView(List<Card> cards);

    @AfterMapping
    void created(Card card, @MappingTarget CardView cardView) {
        if (card.getBack() != null) {
            CardView backCardView = toCardView(card.getBack());
            cardView.setBack(backCardView);
        }
    }
}
