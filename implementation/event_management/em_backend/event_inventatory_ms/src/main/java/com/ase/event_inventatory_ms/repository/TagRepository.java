package com.ase.event_inventatory_ms.repository;

import com.ase.event_inventatory_ms.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
