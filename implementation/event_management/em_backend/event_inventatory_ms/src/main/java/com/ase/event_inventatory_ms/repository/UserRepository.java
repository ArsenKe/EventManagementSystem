package com.ase.event_inventatory_ms.repository;

import com.ase.event_inventatory_ms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}
