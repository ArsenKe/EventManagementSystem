package calender_export_ms.repositories;

import calender_export_ms.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
