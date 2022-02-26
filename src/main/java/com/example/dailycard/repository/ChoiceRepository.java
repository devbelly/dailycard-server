package com.example.dailycard.repository;

import com.example.dailycard.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findAllByCreatedByAndTemplateIdAndBackIsNotNull(String username, Long id);

    Choice findByCreatedByAndId(String username, Long id);
}
