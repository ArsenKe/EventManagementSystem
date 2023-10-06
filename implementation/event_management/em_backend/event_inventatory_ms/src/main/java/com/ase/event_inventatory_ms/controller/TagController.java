package com.ase.event_inventatory_ms.controller;

import com.ase.event_inventatory_ms.entity.CustomListResponse;
import com.ase.event_inventatory_ms.entity.CustomSingleResponse;
import com.ase.event_inventatory_ms.entity.Tag;
import com.ase.event_inventatory_ms.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(path = "/tags", method = RequestMethod.GET)
    public ResponseEntity<CustomListResponse> getTags() {

        try {
            List<Tag> tags = tagService.getTags();
            return ResponseEntity.ok(new CustomListResponse("Tag list!", tags));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomListResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/tags/add", method = RequestMethod.POST)
    public ResponseEntity<CustomSingleResponse> addTag(@RequestBody Tag tag) {
        tagService.addTag(tag);
        try {
            tagService.addTag(tag);
            return ResponseEntity.ok(new CustomSingleResponse("Tag added successfully!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
