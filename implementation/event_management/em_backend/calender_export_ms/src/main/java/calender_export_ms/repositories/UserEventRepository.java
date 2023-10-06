package calender_export_ms.repositories;

import calender_export_ms.entities.Event;
import calender_export_ms.entities.User;
import calender_export_ms.entities.UserEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserEventRepository extends CrudRepository<UserEvent, String> {
    Page<UserEvent> findAllByUserAndTagsNotNull(User user, Pageable pageable);
    Page<UserEvent> findAllByUserAndConfirm(User user, boolean confirmBool, Pageable pageable);
    Page<UserEvent> findAllByUserAndMarked(User user, boolean markedBool, Pageable pageable);
    Optional<UserEvent> getByUserAndEvent(User user, Event event);
}
