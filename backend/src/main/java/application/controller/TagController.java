package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import application.model.Tag;
import application.repository.TagRepository;
import reactor.core.publisher.Mono;

@Controller
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @QueryMapping
    Mono<Iterable<Tag>> tags() {
        return Mono.just(tagRepository.findAll());
    }

    @QueryMapping
    Mono<Tag> getTag(@Argument String id) {
        return Mono.defer(() -> Mono.just(tagRepository.findById(id).orElse(null)));
    }
}
