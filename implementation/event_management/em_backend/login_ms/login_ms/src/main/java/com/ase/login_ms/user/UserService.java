package com.ase.login_ms.user;

import com.ase.login_ms.user.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUserById(long userId) throws Exception {
    return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
  }
}
