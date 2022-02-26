package com.example.dailycard.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

@Getter @Setter
@MappedSuperclass
@JsonIgnoreProperties(
        value={"createdBy","updatedBy"},
        allowGetters = true
)
public abstract class UserDateAudit extends DateAudit{

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
}
