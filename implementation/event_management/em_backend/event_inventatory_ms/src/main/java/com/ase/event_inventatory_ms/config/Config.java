package com.ase.event_inventatory_ms.config;

import com.ase.event_inventatory_ms.entity.*;
import com.ase.event_inventatory_ms.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
public class Config {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//    }

    @Bean
    CommandLineRunner commandLineRunner(
            RoleRepository roleRepository,
            UserRepository userRepository,
            TagRepository tagRepository,
            LocationRepository locationRepository,
            EventRepository eventRepository) {
        return args -> {
            Set<Role> roles = Set.of(new Role(
                    1L,
                    "ADMIN"),
                    new Role(
                            2L,
                            "USER"),
                    new Role(
                            3L,
                            "ORGANIZER"));
            roleRepository.saveAll(roles);

            List<User> users = Arrays.asList(
                    new User(
                            1L,
                            "Md Rafiqul",
                            "Islam",
                            "mri@gmail.com",
                            "12345",
                            roles // admin and user and organizer
            ),
                    new User(
                            2L,
                            "Viktoria",
                            "Siigur",
                            "vs@gmail.com",
                            "12345",
                            roles // admin and user and organizer
            ),
                    new User(
                            3L,
                            "Mohamed Ait",
                            "Amara",
                            "maa@gmail.com",
                            "12345",
                            roles // admin and user and organizer
            ),
                    new User(
                            4L,
                            "Zoheir",
                            "El Houari",
                            "zh@gmail.com",
                            "12345",
                            roles // admin and user and organizer
            ),
                    new User(
                            5L,
                            "Arsen",
                            "K",
                            "ak@gmail.com",
                            "12345",
                            roles // admin and user and organizer
            ));
            userRepository.saveAll(users);

            // ---------------------------tags------------------------
            List<Tag> tags = Arrays.asList(
                    new Tag(
                            1L,
                            "Education"),
                    new Tag(
                            2L,
                            "Sports"),
                    new Tag(
                            3L,
                            "Technology"),
                    new Tag(
                            4L,
                            "Science"),
                    new Tag(
                            5L,
                            "Recruitment"),
                    new Tag(
                            6L,
                            "Music"),
                    new Tag(
                            7L,
                            "Concert")

            );
            tagRepository.saveAll(tags);

            // ---------------------------locations------------------------
            List<Location> locations = Arrays.asList(
                    new Location(1L, 12, "Martingstrasse", 1010, "Vienna", "Austria"),
                    new Location(2L, 2, "Arbeitgasse", 1210, "Graz", "Austria"),
                    new Location(3L, 212, "Meidling", 1110, "Munich", "Germany"),
                    new Location(4L, 212, "Herrengasse", 1110, "Berlin", "Germany"),
                    new Location(5L, 16, "Amir Abd El Kader", 16000, "Algiers", "Algeria"),
                    new Location(6L, 56, "Bashundhara", 1110, "Dhaka", "Bangladesh"));
            locationRepository.saveAll(locations);

            // ---------------------------events------------------------
            Set<Tag> tags1 = Set.of(
                    new Tag(
                            1L,
                            "Education")

            );
            Set<Tag> tags2 = Set.of(
                    new Tag(
                            1L,
                            "Education"),
                    new Tag(
                            2L,
                            "Sports")

            );
            Set<Tag> tags3 = Set.of(
                    new Tag(
                            6L,
                            "Music"),
                    new Tag(
                            7L,
                            "Concert"));
            Set<Tag> tags4 = Set.of(
                    new Tag(
                            1L,
                            "Education"),
                    new Tag(
                            2L,
                            "Sports"),
                    new Tag(
                            6L,
                            "Music"),
                    new Tag(
                            7L,
                            "Concert"));
            Set<Tag> tags5 = Set.of(
                    new Tag(
                            7L,
                            "Concert"));

            List<Event> events = Arrays.asList(
                    new Event(
                            1L,
                            "Slate & Crystal Events",
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                            "2022-05-30T14:30:00",
                            "2022-06-30T14:30:00",
                            locations.get(0),
                            1723,
                            tags1),
                    new Event(
                            2L,
                            "River Stone Events",
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                            "2023-05-30T14:30:00",
                            "2023-06-30T14:30:00",
                            locations.get(1),
                            712,
                            tags2),
                    new Event(
                            3L,
                            "Job hunt",
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                            "2023-05-30T14:30:00",
                            "2023-06-30T14:30:00",
                            locations.get(2),
                            762,
                            tags4),
                    new Event(
                            4L,
                            "Coldplay concert",
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                            "2023-05-30T14:30:00",
                            "2023-06-30T14:30:00",
                            locations.get(3),
                            372,
                            tags3),
                    new Event(
                            5L,
                            "Gamming contest",
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                            "2023-05-30T14:30:00",
                            "2023-06-30T14:30:00",
                            locations.get(4),
                            772,
                            tags5),
                    new Event(
                            6L,
                            "Art Carnival",
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                            "2023-05-30T14:30:00",
                            "2023-06-30T14:30:00",
                            locations.get(5),
                            760,
                            tags3));
            eventRepository.saveAll(events);

        };
    }
}
