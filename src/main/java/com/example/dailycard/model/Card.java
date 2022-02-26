package com.example.dailycard.model;

import com.example.dailycard.model.audit.DateAudit;
import com.example.dailycard.model.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;
    private String mean;
    private Boolean done;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "back", nullable = true)
    private Card back;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field", nullable = false)
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag", nullable = false)
    private Tag tag;
}
