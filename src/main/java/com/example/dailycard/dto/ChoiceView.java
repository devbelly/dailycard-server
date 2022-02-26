package com.example.dailycard.dto;

import lombok.Data;

@Data
public class ChoiceView {

    private Long id;
    private boolean word;
    private boolean mean;
    private ChoiceView back;
    private String description;
}
