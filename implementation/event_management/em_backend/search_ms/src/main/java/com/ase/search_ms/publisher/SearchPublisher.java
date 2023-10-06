//package com.ase.search_ms.publisher;
//
//import com.ase.search_ms.config.MessagingConfig;
//import com.ase.search_ms.entity.Event;
//import com.ase.search_ms.rpc.RPCClient;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//@EnableRabbit
//@RestController
//public class SearchPublisher {
//    @RequestMapping(path = "/test", params = "query", method = RequestMethod.GET)
//    public List<Event> test(@RequestParam("query") String query) {
//        System.out.println("endpoint triggered____controller_______");
//        List<Event> events = new ArrayList<Event>();
//        try {
//            RPCClient rpcClient;
//            Gson gson = new Gson();
//            String response;
//            Type collectionType;
//
//            rpcClient = new RPCClient(MessagingConfig.searchQueryQueue);
//            response = rpcClient.sendMessage(query);
////            collectionType = new TypeToken<List<String>>(){}.getType();
//            collectionType = new TypeToken< List<Event>>(){}.getType();
//            events = gson.fromJson(response, collectionType);
//            System.out.println(events);
//
//
//            return events;
//        } catch (Exception e){
//            e.printStackTrace();
//            return events;
//        }
//    }
//}