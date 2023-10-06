package com.ase.attendance_ms.attendance;

import com.ase.attendance_ms.DTOs.ResponseDTO;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class AttendanceController {

  private final AttendanceService attendanceService;

  public AttendanceController(AttendanceService attendanceService) {
    this.attendanceService = attendanceService;
  }

  @GetMapping("/{eventId}/get-attendees-emails")
  public ResponseEntity<List<String>> getAttendeesEmails(@PathVariable long eventId) {
    return ResponseEntity.ok(attendanceService.getEventAttendeesEmails(eventId));
  }
  @PostMapping("/{eventId}/attend")
  public ResponseEntity<ResponseDTO> createAttendance(@PathVariable long eventId) {
    try {
      attendanceService.createAttendance(eventId);
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity.ok(new ResponseDTO("CONFLICT"));
    }
    return ResponseEntity.ok(new ResponseDTO("SUCCESS"));
  }

  @DeleteMapping("/{eventId}/attend")
  public ResponseEntity<Void> deleteAttendance(@PathVariable long eventId) {
    attendanceService.deleteAttendance(eventId);
    return ResponseEntity.ok().build();
  }
}
