package com.example.dailycard.repository;

import com.example.dailycard.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    Template findByCreatedByAndId(String username, Long id);

    List<Template> findByCreatedBy(String username);
}
