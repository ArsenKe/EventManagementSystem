package report_analytics_ms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import report_analytics_ms.entities.Event;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    Page<Event> findAll(Pageable pageable);
    List<Event> findAllByEndDateTimeAndEventNameAndId(LocalDateTime endTime, String name, String id);
}