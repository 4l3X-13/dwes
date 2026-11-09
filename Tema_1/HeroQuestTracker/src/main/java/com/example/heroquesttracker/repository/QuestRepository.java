package com.example.heroquesttracker.repository;

import com.example.heroquesttracker.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {
    List<Quest> findByHeroName(String heroName);
}
