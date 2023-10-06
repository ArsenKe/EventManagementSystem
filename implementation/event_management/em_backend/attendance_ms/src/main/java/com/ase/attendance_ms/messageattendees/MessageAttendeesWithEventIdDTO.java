package com.ase.attendance_ms.messageattendees;

public record MessageAttendeesWithEventIdDTO(
    Long eventId,
    MessageAttendeesDTO messageAttendeesDTO
) { }
