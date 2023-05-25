package application.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import application.model.Tag;
import application.repository.TagRepository;

@Controller
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @QueryMapping
    Iterable<Tag> tags() {
        return tagRepository.findAll();
    }

    @QueryMapping
    Tag getTag(@Argument Long id) {
        return tagRepository.findById(id).orElse(null);
    }
}
