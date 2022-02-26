package com.example.dailycard.dto;

import com.example.dailycard.model.Choice;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ChoiceRequest {

    private String description;
    private boolean word;
    private boolean mean;
    private ChoiceRequest choiceRequest;
}
