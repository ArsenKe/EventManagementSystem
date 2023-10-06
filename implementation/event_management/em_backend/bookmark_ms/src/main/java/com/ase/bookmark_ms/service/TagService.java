package com.ase.bookmark_ms.service;

import com.ase.bookmark_ms.entity.Tag;
import com.ase.bookmark_ms.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Optional<Tag> tagDetails(Long id) {
        return tagRepository.findById(id);
    }

    public Tag updateTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteTag(long id) {
        tagRepository.deleteById(id);
    }

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }
}