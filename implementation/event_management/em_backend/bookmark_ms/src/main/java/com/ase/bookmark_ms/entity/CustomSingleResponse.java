package com.ase.bookmark_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomSingleResponse {
    private final String message;
    private Object data;
}
