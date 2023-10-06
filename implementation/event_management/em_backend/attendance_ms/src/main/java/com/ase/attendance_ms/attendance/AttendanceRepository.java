package com.ase.attendance_ms.attendance;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CrudRepository<AttendanceEntity, Long> {
  AttendanceEntity findByEventIdAndUserId(long eventId, long userId);

  List<AttendanceEntity> findAllByEventId(long eventId);
}
