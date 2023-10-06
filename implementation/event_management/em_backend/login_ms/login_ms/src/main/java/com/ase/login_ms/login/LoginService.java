package com.ase.login_ms.login;

import com.ase.login_ms.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private final UserService userService;

  public LoginService(UserService userService) {
    this.userService = userService;
  }

  public AuthResponseDTO getToken(AuthRequestDTO authRequestDTO) {
    return new AuthResponseDTO("test-token");
  }

}
