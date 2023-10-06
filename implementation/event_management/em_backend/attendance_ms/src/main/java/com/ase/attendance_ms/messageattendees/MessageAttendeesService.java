package com.ase.attendance_ms.messageattendees;

import com.ase.attendance_ms.attendance.AttendanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MessageAttendeesService {

  @Autowired(required = false)
  private JavaMailSender emailSender;

  private final AttendanceService attendanceService;

  public MessageAttendeesService(AttendanceService attendanceService) {
    this.attendanceService = attendanceService;
  }
  public void sendMessageToAllAttendees(long eventId, MessageAttendeesDTO messageAttendeesDTO) {
    List<String> emails = attendanceService.getEventAttendeesEmails(eventId);
    for (String email: emails) {
      sendMessage(email, messageAttendeesDTO);
    }
  }

  private void sendMessage(String email, MessageAttendeesDTO messageAttendeesDTO) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("aseteam0102@gmail.com");
    message.setTo(email);
    message.setSubject(messageAttendeesDTO.subject());
    message.setText(messageAttendeesDTO.text());
    emailSender.send(message);
  }
}

