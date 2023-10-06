package com.ase.event_inventatory_ms.repository;

import com.ase.event_inventatory_ms.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
