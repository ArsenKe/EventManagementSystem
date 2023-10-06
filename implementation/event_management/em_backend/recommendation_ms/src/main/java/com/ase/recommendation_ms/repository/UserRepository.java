package com.ase.recommendation_ms.repository;

import com.ase.recommendation_ms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}
