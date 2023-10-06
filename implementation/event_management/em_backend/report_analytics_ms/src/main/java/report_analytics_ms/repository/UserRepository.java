package report_analytics_ms.repository;

import org.springframework.data.repository.CrudRepository;
import report_analytics_ms.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
