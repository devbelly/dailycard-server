package com.example.dailycard.dto;

import com.example.dailycard.model.Card;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class CardView {
    private Long id;

    private String word;
    private String mean;
    private Boolean done;

    private CardView back;
}
