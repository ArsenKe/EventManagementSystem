package com.ase.notification_ms.NotifyAttendees;


import com.ase.notification_ms.DTOs.EventDTO;
import com.ase.notification_ms.DTOs.MessageAttendeesDTO;
import com.ase.notification_ms.DTOs.MessageAttendeesWithEventIdDTO;
import com.ase.notification_ms.DTOs.ResponseDTO;
import com.ase.notification_ms.config.MessagingConfig;
import com.ase.notification_ms.utils.RPCClientTemplate;
import com.google.gson.Gson;
import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.TimeoutException;
import org.springframework.stereotype.Service;

@Service
public class NotifyAttendeesService {

  public ResponseDTO notifyAttendees(EventDTO eventDTO)
      throws IOException, TimeoutException, InterruptedException {

    RPCClientTemplate rpcClient = new RPCClientTemplate(
        MessagingConfig.notificationToAttendanceQueryQueue);

    MessageAttendeesDTO newNotification = new MessageAttendeesDTO(
        "Info: " + eventDTO.event_name() + " - event details have changed!",
        "The details of an event you are interested in have changed. \n"
            + "New details: \n"
            + "Name: " + eventDTO.event_name() + "\n"
            + "Description: " + eventDTO.description() + "\n"
            + "Start time: " + eventDTO.start_datetime() + "\n"
            + "End time: " + eventDTO.end_datetime() + "\n"
            + "Location: " + eventDTO.location() + "\n"
            + "Capacity: " + eventDTO.capacity()
    );

    MessageAttendeesWithEventIdDTO messageAttendeesWithEventIdDTO = new MessageAttendeesWithEventIdDTO(eventDTO.id(), newNotification);
    Gson gson = new Gson();
    String notificationInJson = gson.toJson(messageAttendeesWithEventIdDTO);
    return new ResponseDTO(rpcClient.sendMessage(notificationInJson));
  }
}
