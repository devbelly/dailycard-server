package com.example.dailycard.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemplateView {
    private String id;
    private String templateName;
    private List<ChoiceView> choices;
}
