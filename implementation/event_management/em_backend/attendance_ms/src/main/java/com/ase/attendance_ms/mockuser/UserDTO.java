package com.ase.attendance_ms.mockuser;

public record UserDTO(
    long id,
    String firstName,
    String lastName,
    String email,
    String password
) { }
