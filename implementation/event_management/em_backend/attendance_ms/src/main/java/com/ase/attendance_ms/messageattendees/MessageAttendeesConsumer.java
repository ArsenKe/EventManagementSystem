package com.ase.attendance_ms.messageattendees;

import com.ase.attendance_ms.config.MessagingConfig;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageAttendeesConsumer {

  private final MessageAttendeesService messageAttendeesService;

  public MessageAttendeesConsumer(MessageAttendeesService messageAttendeesService) {
    this.messageAttendeesService = messageAttendeesService;
  }

  @RabbitListener(queues = MessagingConfig.notificationToAttendanceQueryQueue)
  public String getNotificationMessage(String notificationMessage) {
    Gson gson = new Gson();
    MessageAttendeesWithEventIdDTO messageAttendeesWithEventIdDTO =
        gson.fromJson(notificationMessage, MessageAttendeesWithEventIdDTO.class);
    Long eventId = messageAttendeesWithEventIdDTO.eventId();
    MessageAttendeesDTO messageAttendeesDTO = messageAttendeesWithEventIdDTO.messageAttendeesDTO();
    messageAttendeesService.sendMessageToAllAttendees(eventId, messageAttendeesDTO);
    return "Notification has been sent to all the attendees!";
  }

}
