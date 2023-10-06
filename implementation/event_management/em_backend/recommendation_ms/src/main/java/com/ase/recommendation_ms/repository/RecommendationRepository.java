package com.ase.recommendation_ms.repository;

import com.ase.recommendation_ms.entity.Event;
import com.ase.recommendation_ms.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface RecommendationRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e")
    List<Event> getAllEvents();




//    List<Event> getRecommendationsForUser();



}
