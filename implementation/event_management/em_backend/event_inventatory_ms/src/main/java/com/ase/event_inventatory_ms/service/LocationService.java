package com.ase.event_inventatory_ms.service;

import com.ase.event_inventatory_ms.entity.Location;
import com.ase.event_inventatory_ms.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    public Optional<Location> locationDetails(Long id) {
        return locationRepository.findById(id);
    }

    public void updateLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocation(long id) {
        locationRepository.deleteById(id);
    }

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }
    
}