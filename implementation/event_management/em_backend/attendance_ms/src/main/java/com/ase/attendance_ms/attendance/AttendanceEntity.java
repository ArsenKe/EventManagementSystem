package com.ase.attendance_ms.attendance;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "ATTENDANCE",
    uniqueConstraints = @UniqueConstraint(columnNames={"event_id", "user_id"})
)
@Getter
@Setter
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name="event_id")
    long eventId;
    @Column(name="user_id")
    long userId;
}
