package com.ase.search_ms.controller;

import com.ase.search_ms.config.MessagingConfig;
import com.ase.search_ms.entity.CustomListResponse;
import com.ase.search_ms.entity.Event;
import com.ase.search_ms.utils.RPCClientTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@EnableRabbit
@RestController
public class SearchController {
    @SneakyThrows
    @RequestMapping(path = "/search", params = "query", method = RequestMethod.GET)
    public ResponseEntity<CustomListResponse> search(@RequestParam("query") String query) {
        System.out.println("******************************From search controller******************************");
        List<Event> filteredEvents = new ArrayList<Event>();
        try {
            Gson gson = new Gson();                                                 //to serialize data
            RPCClientTemplate rpcClient = new RPCClientTemplate(MessagingConfig.searchQueryQueue);  //creating RPC client and passing queue name for send message
            String response = rpcClient.sendMessage(query);                         //call send method to send the data to queue
            Type collectionType = new TypeToken<List<Event>>() {
            }.getType();        //response type
            filteredEvents = gson.fromJson(response, collectionType);               //converting json response to JPA
//            System.out.println(events);

            return ResponseEntity.ok(new CustomListResponse("Search result of the events!", filteredEvents));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomListResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
