package com.ase.search_ms.entity;

import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomListResponse {
    private final String message;
    List<?> data;
}
