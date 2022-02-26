package com.example.dailycard.repository;

import com.example.dailycard.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByCreatedBy(String createdBy);

    Tag findByName(String name);

    Tag findByCreatedByAndName(String username, String name);

    Tag findByCreatedByAndId(String username, Long id);
}
