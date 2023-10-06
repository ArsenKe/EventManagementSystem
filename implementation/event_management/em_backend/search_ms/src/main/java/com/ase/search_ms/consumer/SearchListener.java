//package com.ase.search_ms.consumer;
//
//import com.ase.search_ms.config.MessagingConfig;
//import com.ase.search_ms.entity.Event;
//import com.ase.search_ms.entity.Location;
//import com.ase.search_ms.entity.Tag;
//import com.google.gson.Gson;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeoutException;
//
//@Component
//public class SearchListener {
//
//    @RabbitListener(queues = MessagingConfig.searchQueryQueue)
//    public String preferenceChange(String query) throws IOException, TimeoutException, InterruptedException {
//        System.out.println("******************************From search listener******************************");
//        System.out.println(query);
//
//        Set<Tag> tags = Set.of(
//                new Tag(
//                        1L,
//                        "Education"
//                ),
//                new Tag(
//                        2L,
//                        "Sports"
//                ),
//                new Tag(
//                        3L,
//                        "Technology"
//                ),
//                new Tag(
//                        4L,
//                        "Science"
//                )
//
//        );
//        //---------------------------locations------------------------
//        List<Location> locations = Arrays.asList(
//                new Location(1L, 12, "Martingstrasse", 1010, "Vienna", "Austria"),
//                new Location(2L, 2, "Arbeitgasse", 1210, "Graz", "Austria"),
//                new Location(3L, 212, "Meidling", 1110, "Munich", "Germany")
//        );
//
//
//        List<Event> events = Arrays.asList(
//                new Event(
//                        1L,
//                        "Slate & Crystal Events",
//                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
//                        "2022-05-30T14:30:00",
//                        "2022-06-30T14:30:00",
//                        locations.get(0),
//                        123,
//                        tags
//                ),
//                new Event(
//                        2L,
//                        "River Stone Events",
//                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
//                        "2023-05-30T14:30:00",
//                        "2023-06-30T14:30:00",
//                        locations.get(2),
//                        12,
//                        tags
//                )
//        );
//
//        Gson gson = new Gson();
//        return gson.toJson(events);
//    }
//}