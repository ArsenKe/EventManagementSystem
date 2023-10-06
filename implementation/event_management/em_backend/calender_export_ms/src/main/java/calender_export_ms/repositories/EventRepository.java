package calender_export_ms.repositories;

import calender_export_ms.entities.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EventRepository extends CrudRepository<Event, String> {
    Optional<Event> findByEventName(String eventName);

  //  Object findByEventNameIn(List<Object> anyList);
}
