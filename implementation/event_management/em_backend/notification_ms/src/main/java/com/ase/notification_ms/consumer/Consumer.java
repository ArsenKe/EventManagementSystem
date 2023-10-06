package com.ase.notification_ms.consumer;

import com.ase.notification_ms.config.MessagingConfig;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

//  @RabbitListener(queues = MessagingConfig.notificationToAttendanceQueryQueue)
//  public void getQuery(String query) throws IOException, TimeoutException, InterruptedException {
//  System.out.println(query);
//   // List<Event> events = eventService.searchEvents(query);
//  }

}
