package com.example.dailycard.model;

import com.example.dailycard.dto.FieldRequest;
import com.example.dailycard.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"template"})
@Entity
@Builder
public class Choice extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean word;
    private Boolean mean;

    /*
    추가됨
     */
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    @JsonBackReference
    private Template template;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "back", nullable = true)
    private Choice back;

    public void setBack(Choice backChoice) {
        this.back.setBack(backChoice);
    }

    public Card createCard(Field fieldRequest) {
        Card card = new Card();
        card.setField(fieldRequest);
        if (word == true) {
            card.setWord(fieldRequest.getWord());
        }
        if (mean == true) {
            card.setMean(fieldRequest.getMean());
        }
        if (back != null) {
            card.setBack(back.createCard(fieldRequest));
        }
        return card;
    }
}
