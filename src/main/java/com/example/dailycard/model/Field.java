package com.example.dailycard.model;

import com.example.dailycard.model.audit.DateAudit;
import com.example.dailycard.model.audit.UserDateAudit;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** DateAudit + User field vs UserDateAudit */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"template", "cards"})
public class Field extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;
    private String mean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;

    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "user_id")
    //    private User user;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
