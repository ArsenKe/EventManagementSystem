package com.ase.search_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private String eventName;
    private String description;
    private String startDatetime;
    private String endDatetime;
    private Location location;
    private Integer capacity;
    private Set<Tag> tags = new HashSet<>();
}
