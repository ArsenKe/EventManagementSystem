package com.ase.event_inventatory_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomListResponse {
    private final String message;
    List<?> data;
}
