package report_analytics_ms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import report_analytics_ms.entities.Event;
import report_analytics_ms.entities.User;
import report_analytics_ms.entities.UserEvent;

import java.util.List;
import java.util.Optional;

public interface UserEventRepository extends CrudRepository<UserEvent, String> {
    Page<UserEvent> findAllByUser(User user, Pageable pageable);
    List<UserEvent> findAllByEvent(Event event);
    int countByEventAndMarked(Event event,boolean marked);
    int countByEventAndAttendance(Event Event,boolean attendance);
    Optional<UserEvent> getByUserAndEvent(User user, Event event);
}
