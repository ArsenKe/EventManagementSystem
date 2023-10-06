package com.ase.search_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private Long id;
    private Integer streetNumber;
    private String street;
    private Integer zip;
    private String city;
    private String country;
}
