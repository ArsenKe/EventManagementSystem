//package com.ase.bookmark_ms.service;
//
//import com.ase.bookmark_ms.entity.Tag;
//import com.ase.bookmark_ms.repository.TagRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class TagServiceTest {
//
//    @Autowired
//    private TagService service;
//
//    @MockBean
//    private TagRepository repository;
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
//    void addTagTest() {
//        Tag tag = new Tag(1L, "Tag1");
//        when(repository.save(tag)).thenReturn(tag);
//        assertEquals(tag, service.addTag(tag));
//    }
//
//    @Test
//    void tagDetailsTest() {
//        Long id = 1L;
//        Tag tag_mock = new Tag(1L, "Tag1");
//        when(repository.findById(id))
//                .thenReturn(Optional.of(tag_mock));
//        assertEquals(Optional.of(tag_mock), service.tagDetails(id));
//    }
//
//    @Test
//    void updateTagTest() {
//        //mocking dB call
//        Tag tag = new Tag(1L, "Technology");
//        tag.setName("Sport");
//        Long id = 1L;
//        when(repository.findById(id)).thenReturn(Optional.of(tag));
//        assertEquals(tag.getName(),"Sport");
//    }
//
//    @Test
//    void deleteTagTest() {
//        Tag tag = new Tag(1L, "Tag1");
//        // given
//        Long id = tag.getId();
//        given(repository.existsById(id))
//                .willReturn(true);
//        // when
//        service.deleteTag(id);
//        // then
//        verify(repository).deleteById(id);
//    }
//
//    @Test
//    public void getTagsTest() {
//        when(repository.findAll()).thenReturn(Stream
//                .of(new Tag(1L, "Tag1"), new Tag(2L, "Tag2")).collect(Collectors.toList()));
//        assertEquals(2, service.getTags().size());
//    }
//}