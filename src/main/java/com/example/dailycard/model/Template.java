package com.example.dailycard.model;

import com.example.dailycard.dto.FieldRequest;
import com.example.dailycard.model.audit.DateAudit;
import com.example.dailycard.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"choices"})
@Entity
@Slf4j
public class Template extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Choice> choices;

    public List<Card> createCards(Field field) {
        List<Card> cards = new ArrayList<Card>();
        for (Choice choice : choices) {
            if (choice.getBack() == null) continue;
            Card card = choice.createCard(field);
            card.setDone(false);
            cards.add(card);
        }
        return cards;
    }
}
