//package com.ase.bookmark_ms.config;
//
//import com.ase.bookmark_ms.entity.*;
//import com.ase.bookmark_ms.repository.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Configuration
//public class Config {
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            TagRepository tagRepository,
//            BookmarkRepository bookmarkRepository) {
//        return args -> {
//            Set<Role> roles = Set.of(new Role(
//                    1L,
//                    "ADMIN"), new Role(
//                    2L,
//                    "USER"), new Role(
//                    3L,
//                    "ORGANIZER"));
//
////            ---------------------------tags------------------------
//            List<Tag> tags = Arrays.asList(
//                    new Tag(
//                            1L,
//                            "Education"
//                    ),
//                    new Tag(
//                            2L,
//                            "Sports"
//                    ),
//                    new Tag(
//                            3L,
//                            "Technology"
//                    ),
//                    new Tag(
//                            4L,
//                            "Science"
//                    ),
//                    new Tag(
//                            5L,
//                            "Recruitment"
//                    ),
//                    new Tag(
//                            6L,
//                            "Music"
//                    ),
//                    new Tag(
//                            7L,
//                            "Concert"
//                    )
//
//            );
//            tagRepository.saveAll(tags);
//
//
//            //            ---------------------------events------------------------
//            Set<Tag> tags1 = Set.of(
//                    new Tag(
//                            1L,
//                            "Education"
//                    )
//            );
////            ---------------------------locations------------------------
//            List<Location> locations = Arrays.asList(
//                    new Location(1L, 12, "Martingstrasse", 1010, "Vienna", "Austria"),
//                    new Location(2L, 2, "Arbeitgasse", 1210, "Graz", "Austria"),
//                    new Location(3L, 212, "Meidling", 1110, "Munich", "Germany"),
//                    new Location(4L, 212, "Herrengasse", 1110, "Berlin", "Germany"),
//                    new Location(5L, 16, "Amir Abd El Kader", 16000, "Algiers", "Algeria"),
//                    new Location(6L, 56, "Bashundhara", 1110, "Dhaka", "Bangladesh")
//            );
//
////            bookmark--------------------------------------------------------------------------------
//            List<Bookmark> bookmarks = Arrays.asList(
//                    new Bookmark(
//                            1L,
//                            new Event(
//                                    1L,
//                                    "Slate & Crystal Events",
//                                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
//                                    "2022-05-30T14:30:00",
//                                    "2022-06-30T14:30:00",
//                                    locations.get(0),
//                                    123,
//                                    tags1
//                            ),
//                            new User(
//                                    1L,
//                                    "Md Rafiqul",
//                                    "Islam",
//                                    "mri@gmail.com",
//                                    "12345",
//                                    roles //admin and user and organizer
//                            )
//                    )
//            );
//            bookmarkRepository.saveAll(bookmarks);
//        };
//    }
//}
