package com.ase.recommendation_ms.controller;

import com.ase.recommendation_ms.config.MessagingConfig;
import com.ase.recommendation_ms.entity.*;
import com.ase.recommendation_ms.service.RecommendationService;
import com.ase.recommendation_ms.utils.RPCClientTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.RpcClient;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EnableRabbit
@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;


    @RequestMapping(path = "/recommendation", params = "user_id", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getUserRecommendations(@RequestParam("user_id") Long user_id) {

        List<Event> recommendedEvents = new ArrayList<>();
        List<Feedback> allFeedbacks = new ArrayList<>();
        List<Bookmark> userBookmarks = new ArrayList<>();

          try{
              Gson gson = new Gson();

              // getting the user bookmarks
              RPCClientTemplate rpcClient1 = new RPCClientTemplate(MessagingConfig.bookmarkQueue);
              String Bookmarks = rpcClient1.sendMessage(user_id.toString());
              Type collectionType = new TypeToken<List<Bookmark>>() {}.getType();        //response type
              userBookmarks = gson.fromJson(Bookmarks, collectionType);

              List<Tag> userBookmarkedEventTags = new ArrayList<>();
              List<Event> eventsBooked = new ArrayList<>();
              List<Long> eventIds = new ArrayList<>();

//              // get the list of tags of the user's bookmarked events
                for (Bookmark bookmark : userBookmarks) {
                    eventIds.add(bookmark.getEventId());
                }

              RPCClientTemplate rpcClient2 = new RPCClientTemplate(MessagingConfig.eventsByIdsQueue);
                String bookmarkedEvents = rpcClient2.sendMessage(eventIds.toString());
              Type collectionType2 = new TypeToken<List<Event>>() {}.getType();

              eventsBooked = gson.fromJson(bookmarkedEvents,collectionType2);

              // get the tags of eventsBooked
              for (Event event : eventsBooked){
                  userBookmarkedEventTags.addAll(event.getTags());
              }
              List<Long> tagIds = new ArrayList<>();
              for(Tag tag : userBookmarkedEventTags){
                  tagIds.add(tag.getId());
              }

              // find a list of events that has the same tags as the user's bookmarked events
              RPCClientTemplate rpcClient3 = new RPCClientTemplate(MessagingConfig.eventsByTagsIdQueue);
                String Events = rpcClient3.sendMessage(tagIds.toString());

//                System.out.println("events: " + Events);
                Type collectionType3 = new TypeToken<List<Event>>() {}.getType();        //response type
                List<Event> eventsThatMatchUserBookmarksTags = gson.fromJson(Events, collectionType3);


              // delete the events that the user has already bookmarked
              recommendedEvents = eventsThatMatchUserBookmarksTags.stream()
                      .filter(event -> !eventIds.contains(event.getId()))
                      .toList();


              return ResponseEntity.ok(recommendedEvents);



          } catch (Exception e) {
              e.printStackTrace();
          }





        return null;
    }


}
