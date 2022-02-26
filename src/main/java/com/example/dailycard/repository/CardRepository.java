package com.example.dailycard.repository;

import com.example.dailycard.model.Card;
import com.example.dailycard.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByCreatedByAndTagAndBackIsNotNull(String username, Tag tag);
}
