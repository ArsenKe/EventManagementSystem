package com.ase.recommendation_ms.repository;

import com.ase.recommendation_ms.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(Long userId);

//    List<Bookmark> findByEventId(Long eventId);
//    Optional<Bookmark> findByUserIdAndEventId(Long userId, Long eventId);
}
