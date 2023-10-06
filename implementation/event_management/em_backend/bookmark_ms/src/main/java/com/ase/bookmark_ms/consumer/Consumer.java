package com.ase.bookmark_ms.consumer;

import com.ase.bookmark_ms.entity.Bookmark;
import com.ase.bookmark_ms.service.BookmarkService;
import com.ase.bookmark_ms.config.MessagingConfig;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Component
public class Consumer {

    @Autowired
    private BookmarkService bookmarkService;

    // for recommendations ms-------------------
    @RabbitListener(queues = MessagingConfig.bookmarkQueue)
    public String getBookmarksByUserId(String userId) throws IOException, TimeoutException, InterruptedException {
//        System.out.println("******************************From search listener******************************");
//        System.out.println(userId);
        // convert the userId to long
        Long userIdLong = Long.parseLong(userId);


        List<Bookmark> bookmarks = bookmarkService.getBookmarksByUserId(userIdLong);

        Gson gson = new Gson();
        return gson.toJson(bookmarks);
    }
}