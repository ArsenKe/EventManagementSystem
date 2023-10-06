package com.ase.bookmark_ms.controller;

import com.ase.bookmark_ms.config.MessagingConfig;
import com.ase.bookmark_ms.entity.*;
import com.ase.bookmark_ms.service.BookmarkService;
import com.ase.bookmark_ms.utils.RPCClientTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;

    //    todo: add/remove tag to event would be same as update event

    @RequestMapping(path = "/bookmarks", params = "user_id", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBookmarks(@RequestParam("user_id") Long user_id) {
//        request sample--------
//        http://localhost:8080/bookmarks?user_id=1
        try {
//            System.out.println(user_id);
//            List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
            List<Bookmark> bookmarks = bookmarkService.getBookmarksByUserId(user_id);
            return ResponseEntity.ok(new CustomListResponse("Bookmarked Events!", bookmarks));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomListResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/bookmarks/add", method = RequestMethod.POST)
    public ResponseEntity<?> markEvent(@RequestBody Bookmark bookmark) {
        System.out.println("Debugging hrere.......");
        System.out.println(bookmark.getId());
        System.out.println(bookmark.getEventId());
        System.out.println(bookmark.getUserId());
//        demo request body
//        {
//            "event" :{
//            "id":2
//            },
//            "user":{
//            "id":1
//            }
//        }
//        todo get events from event inventory and map then send to ui
        try {
            bookmarkService.addBookmark(bookmark);
            return ResponseEntity.ok(new CustomSingleResponse("Events added to bookmark list!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/bookmarks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> unMarkEvent(@PathVariable("id") Long id) {
        try {
            bookmarkService.deleteBookmark(id);
            return ResponseEntity.ok(new CustomSingleResponse("Events removed from bookmark list!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
