//package com.ase.bookmark_ms.service;
//
//import com.ase.bookmark_ms.entity.*;
//import com.ase.bookmark_ms.repository.BookmarkRepository;
//import com.ase.bookmark_ms.repository.TagRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class BookmarkServiceTest {
//
//    @Autowired
//    private BookmarkService service;
//
//    @MockBean
//    private BookmarkRepository repository;
//
//    //mock data-------------
//    Set<Tag> tags1 = Set.of(
//            new Tag(
//                    1L,
//                    "Education"
//            ),
//            new Tag(
//                    2L,
//                    "Sports"
//            ));
//    List<Location> locations = Arrays.asList(
//            new Location(1L, 12, "Martingstrasse", 1010, "Vienna", "Austria"),
//            new Location(2L, 2, "Arbeitgasse", 1210, "Graz", "Austria"),
//            new Location(3L, 212, "Meidling", 1110, "Munich", "Germany")
//    );
//    Event event = new Event(
//            1L,
//            "Slate & Crystal Events",
//            "Lorem Ipsum is simply dummy text of the printing.",
//            "2022-05-30T14:30:00",
//            "2022-06-30T14:30:00",
//            locations.get(0),
//            123,
//            tags1
//    );
//    Set<Role> roles = Set.of(new Role(
//            2L,
//            "ORGANIZER"));
//    User user1 = new User(
//            1L,
//            "Md R",
//            "Islam",
//            "mri@gmail.com",
//            "12345",
//            roles
//    );
//    User user2 = new User(
//            2L,
//            "Mohamed",
//            "Ait",
//            "ma@gmail.com",
//            "12345",
//            roles
//    );
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getAllBookmarksTest() {
//        when(repository.findAll()).thenReturn(Stream
//                .of(new Bookmark(1L, event, user1)).collect(Collectors.toList()));
//        assertEquals(1, service.getAllBookmarks().size());
//    }
//
//    @Test
//    void getBookmarksByUserIdTest() {
//        Long userId = 1L;
//        when(repository.findByUserId(userId)).thenReturn(Stream
//                .of(new Bookmark(1L, event, user1)).collect(Collectors.toList()));
//        assertEquals(1, service.getBookmarksByUserId(userId).size());
//    }
//
//    @Test
//    void addBookmark() {
//        Bookmark bookmark = new Bookmark(1L, event, user1);
//        when(repository.save(bookmark)).thenReturn(bookmark);
//        assertEquals(bookmark, service.addBookmark(bookmark));
//    }
//
//    @Test
//    void deleteBookmark() {
//        Bookmark bookmark = new Bookmark(1L, event, user1);
//        // given
//        Long id = bookmark.getId();
//        given(repository.existsById(id))
//                .willReturn(true);
//        // when
//        service.deleteBookmark(id);
//        // then
//        verify(repository).deleteById(id);
//    }
//}