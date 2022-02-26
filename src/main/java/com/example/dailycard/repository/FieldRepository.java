package com.example.dailycard.repository;

import com.example.dailycard.model.Field;
import com.example.dailycard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByCreatedBy(String username);

    Field findByCreatedByAndId(String username, Long id);
}
