package com.ase.notification_ms.NotifyAttendees;


import com.ase.notification_ms.DTOs.EventDTO;
import com.ase.notification_ms.DTOs.ResponseDTO;
import com.ase.notification_ms.config.MessagingConfig;
import com.ase.notification_ms.utils.RPCClientTemplate;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyAttendeesController {
  private final NotifyAttendeesService notifyAttendeesService;

  public NotifyAttendeesController(NotifyAttendeesService notifyAttendeesService) {
    this.notifyAttendeesService = notifyAttendeesService;
  }

  @PostMapping("events/notify-attendees")
  public ResponseEntity<ResponseDTO> sendMessage(@RequestBody EventDTO eventDTO)
      throws IOException, TimeoutException, InterruptedException {

    ResponseDTO response = notifyAttendeesService.notifyAttendees(eventDTO);
    return ResponseEntity.ok(response);
  }
}
