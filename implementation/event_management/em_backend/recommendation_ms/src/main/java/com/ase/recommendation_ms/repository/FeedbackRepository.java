package com.ase.recommendation_ms.repository;

import com.ase.recommendation_ms.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f")
    List<Feedback> getAllFeedbacks();
}
