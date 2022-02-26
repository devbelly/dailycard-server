package com.example.dailycard.dto;

import lombok.Data;

@Data
public class FieldRequest {

    private Long templateNumber;
    private String tag;
    private String word;
    private String mean;
}
