package com.ase.bookmark_ms.service;

import com.ase.bookmark_ms.entity.Bookmark;
import com.ase.bookmark_ms.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<Bookmark> getBookmarksByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    public Bookmark addBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(Long bookmarkId) {
//        Optional<Bookmark> bookmark = bookmarkRepository.findByUserIdAndEventId(userId, eventId);
//        bookmark.ifPresent(value -> bookmarkRepository.delete(value));
        bookmarkRepository.deleteById(bookmarkId);
    }
}