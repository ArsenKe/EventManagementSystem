package com.ase.attendance_ms.mockuser;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static UserDTO getUserById(long userId) {
        return new UserDTO(
            userId,
            "TestFirstName",
            "TestLastName",
            "viktoria.siigur@gmail.com",
            "password"
        );
    }
}
