package com.ase.notification_ms.DTOs;

public record EventDTO(
    Long id,
    String event_name,
    String description,
    String start_datetime,
    String end_datetime,
    LocationDTO location,
    Integer capacity
) {

}
