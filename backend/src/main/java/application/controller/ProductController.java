package application.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import application.model.Product;
import application.model.Tag;
import application.repository.ProductRepository;
import application.repository.TagRepository;
import reactor.core.publisher.Mono;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

    @QueryMapping
    Mono<List<Product>> getAllProducts() {
        return Mono.just(productRepository.findAll());
    }

    @QueryMapping
    Mono<Optional<Product>> getProduct(@Argument String id) {
        return Mono.just(productRepository.findById(id));
    }

    @QueryMapping
    Mono<List<Product>> searchProductsByTagNames(@Argument List<String> tags) {
        return Mono.just(productRepository.findByTagNames(tags));
    }

    @MutationMapping
    Mono<Product> createProduct(@Argument String name, @Argument String description, @Argument Double price,
            @Argument List<String> tags) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCreatedAt(LocalDateTime.now());

        List<Tag> tagList = tagRepository.findByNameIn(tags);
        product.setTags(tagList);

        return Mono.just(productRepository.save(product));
    }

    @MutationMapping
    Mono<Product> updateProduct(@Argument String id, @Argument String name, @Argument String description,
            @Argument Double price, @Argument List<String> tags) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            if (name != null) {
                product.setName(name);
            }
            if (description != null) {
                product.setDescription(description);
            }
            if (price != null) {
                product.setPrice(price);
            }
            if (tags != null) {
                List<Tag> tagList = tagRepository.findByNameIn(tags);
                product.setTags(tagList);
            }

            return Mono.just(productRepository.save(product));
        }

        return Mono.empty();
    }

    @MutationMapping
    Mono<Boolean> deleteProduct(@Argument String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return Mono.just(true);
        }

        return Mono.just(false);
    }
}
