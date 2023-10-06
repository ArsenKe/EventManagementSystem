package com.ase.attendance_ms.messageattendees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class MessageAttendeesController {

  private final MessageAttendeesService attendanceService;

  public MessageAttendeesController(MessageAttendeesService attendanceService) {
    this.attendanceService = attendanceService;
  }

  @PostMapping("/{eventId}/send-message")
  public ResponseEntity<Void> sendMessage(@PathVariable long eventId, @RequestBody MessageAttendeesDTO messageAttendeesDTO) {
    attendanceService.sendMessageToAllAttendees(eventId, messageAttendeesDTO);
    return ResponseEntity.ok().build();
  }
}
