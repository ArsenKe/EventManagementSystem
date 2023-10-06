package com.ase.event_inventatory_ms.repository;

import com.ase.event_inventatory_ms.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE " +
            "LOWER(e.eventName) LIKE LOWER(CONCAT('%', TRIM(:query), '%')) OR " +
            "LOWER(e.description) LIKE LOWER(CONCAT('%', TRIM(:query), '%')) OR " +
            "LOWER(e.location.street) LIKE LOWER(CONCAT('%', TRIM(:query), '%')) OR " +
            "LOWER(e.location.city) LIKE LOWER(CONCAT('%', TRIM(:query), '%')) OR " +
            "LOWER(e.location.country) LIKE LOWER(CONCAT('%', TRIM(:query), '%')) "+
            "OR EXISTS (SELECT t FROM e.tags t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', TRIM(:query), '%')))" )
    List<Event> searchEvents(@Param("query") String query);

    @Query("SELECT e FROM Event e WHERE e.id IN (:eventIds)")
    List<Event> findAllByIds(@Param("eventIds") List<Long> eventIds);

    List<Event> findAll();


}


