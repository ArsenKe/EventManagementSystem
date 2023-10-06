package com.ase.event_inventatory_ms.controller;

import com.ase.event_inventatory_ms.entity.CustomListResponse;
import com.ase.event_inventatory_ms.entity.CustomSingleResponse;
import com.ase.event_inventatory_ms.entity.Location;
import com.ase.event_inventatory_ms.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping(path = "/locations/add", method = RequestMethod.POST)
    public ResponseEntity<CustomSingleResponse> addLocation(@RequestBody Location location) {
        try {
            locationService.addLocation(location);
            return ResponseEntity.ok(new CustomSingleResponse("Location added successfully!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/locations", method = RequestMethod.GET)
    public ResponseEntity<CustomListResponse> getLocations() {

        try {
            List<Location> locations = locationService.getLocations();
            return ResponseEntity.ok(new CustomListResponse("Location list!", locations));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomListResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/locations/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomSingleResponse> locationDetails(@PathVariable("id") Long id) {

        try {
            Optional<Location> location = locationService.locationDetails(id);
            return ResponseEntity.ok(new CustomSingleResponse("Location details!", location));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/locations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CustomSingleResponse> deleteLocation(@PathVariable("id") Long id) {

        try {
            locationService.deleteLocation(id);
            return ResponseEntity.ok(new CustomSingleResponse("Location deleted!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/locations/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CustomSingleResponse> updateLocation(@RequestBody Location location, @PathVariable("id") Long id) {

        try {
            location.setId(id);
            locationService.updateLocation(location);
            return ResponseEntity.ok(new CustomSingleResponse("Location updated!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
