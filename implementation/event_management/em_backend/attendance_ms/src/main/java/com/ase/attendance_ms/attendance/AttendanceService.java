package com.ase.attendance_ms.attendance;

import com.ase.attendance_ms.mockuser.UserDTO;
import com.ase.attendance_ms.mockuser.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

  private final AttendanceRepository attendanceRepository;

  public AttendanceService(AttendanceRepository attendanceRepository) {
    this.attendanceRepository = attendanceRepository;
  }

  public void createAttendance(long eventId) {
    long userId = 1234; // TODO: Get Id from the token;

    // TODO: Check vacancies

    AttendanceEntity attendanceEntity = new AttendanceEntity();
    attendanceEntity.setEventId(eventId);
    attendanceEntity.setUserId(userId);

    attendanceRepository.save(attendanceEntity);
  }

  // TODO: Delete this.
  public void deleteAttendance(long eventId) {
    long userId = 1234; // TODO: Get Id from the token;
    AttendanceEntity attendanceEntity = attendanceRepository.findByEventIdAndUserId(eventId, userId);
    attendanceRepository.deleteById(attendanceEntity.getId());
  }

  public List<String> getEventAttendeesEmails(long eventId) {

    List<Long> userIDs = attendanceRepository
        .findAllByEventId(eventId)
        .stream()
        .map(AttendanceEntity::getUserId)
        .toList();

    // TODO: Get users from real UserService

    List<UserDTO> users = userIDs
        .stream()
        .map(UserService::getUserById)
        .toList();

    return users
        .stream()
        .map(UserDTO::email)
        .toList();
  }
}

