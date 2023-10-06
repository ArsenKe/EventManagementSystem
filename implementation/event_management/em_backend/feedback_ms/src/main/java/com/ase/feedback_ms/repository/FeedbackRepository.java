package com.ase.feedback_ms.repository;

import com.ase.feedback_ms.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByEventId(Long eventId);
    Feedback findByFeedbackId(Long feedbackId);

    List<Feedback> findAll();

    @Query(value ="SELECT f from Feedback f WHERE f.eventId IN (:eventIds)")
    List<Feedback> findByEventIds(List<Long> eventIds);

}
