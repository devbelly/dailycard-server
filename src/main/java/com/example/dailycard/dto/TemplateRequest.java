package com.example.dailycard.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class TemplateRequest {

    @NotBlank private String templateName;

    private List<ChoiceRequest> choices = new ArrayList<>();
}
