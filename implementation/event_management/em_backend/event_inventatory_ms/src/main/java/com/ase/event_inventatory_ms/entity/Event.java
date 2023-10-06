package com.ase.event_inventatory_ms.entity;


//import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String description;
    private String startDatetime;
    private String endDatetime;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Location location;
    private Integer capacity;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "event_tag",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
}
