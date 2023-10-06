package com.ase.notification_ms.DTOs;

public record MessageAttendeesWithEventIdDTO(
    Long eventId,
    MessageAttendeesDTO messageAttendeesDTO
) { }
