package com.ase.login_ms.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class LoginController {

  private final LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }


  @PostMapping("login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authDTO) {
    AuthResponseDTO authResponseDTO = loginService.getToken(authDTO);
    return ResponseEntity.ok(authResponseDTO);
  }
}
