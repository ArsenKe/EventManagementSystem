package com.ase.event_inventatory_ms.controller;

import com.ase.event_inventatory_ms.entity.CustomListResponse;
import com.ase.event_inventatory_ms.entity.CustomSingleResponse;
import com.ase.event_inventatory_ms.entity.User;
import com.ase.event_inventatory_ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/users/add", method = RequestMethod.POST)
    public ResponseEntity<CustomSingleResponse> addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.ok(new CustomSingleResponse("User added successfully!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ResponseEntity<CustomListResponse> getUsers() {

        try {
            List<User> users = userService.getUsers();
            return ResponseEntity.ok(new CustomListResponse("User list!", users));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomListResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomSingleResponse> userDetails(@PathVariable("id") Long id) {

        try {
            Optional<User> user = userService.userDetails(id);
            return ResponseEntity.ok(new CustomSingleResponse("User details!", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CustomSingleResponse> deleteUser(@PathVariable("id") Long id) {

        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(new CustomSingleResponse("User deleted!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CustomSingleResponse> updateUser(@RequestBody User user, @PathVariable("id") Long id) {

        try {
            user.setId(id);
            userService.updateUser(user);
            return ResponseEntity.ok(new CustomSingleResponse("User updated!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
