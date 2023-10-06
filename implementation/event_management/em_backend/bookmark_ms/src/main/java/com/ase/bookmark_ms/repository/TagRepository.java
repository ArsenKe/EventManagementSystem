package com.ase.bookmark_ms.repository;

import com.ase.bookmark_ms.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
