package application.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import application.model.Product;
import application.model.Tag;
import application.repository.ProductRepository;
import application.repository.TagRepository;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    public ProductController(ProductRepository productRepository, TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
    }

    @QueryMapping
    Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    Product getProduct(@Argument String uuid) {
        return productRepository.findByUuid(uuid);
    }

    @QueryMapping
    List<String> searchProductsByTagNames(@Argument List<String> tags) {
        return productRepository.findUuidsByTagNames(tags);
    }

    @MutationMapping
    Product createProduct(@Argument String name, @Argument String description, @Argument Double price,
            @Argument List<String> tags) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        List<Tag> tagList = tagRepository.findByNameIn(tags);
        product.setTags(tagList);

        return productRepository.save(product);
    }

    @MutationMapping
    Product updateProduct(@Argument String uuid, @Argument String name, @Argument String description,
            @Argument Double price, @Argument List<String> tags) {
        Product product = productRepository.findByUuid(uuid);

        if (product != null) {
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

            return productRepository.save(product);
        }

        return null;
    }

    @MutationMapping
    Boolean deleteProduct(@Argument String uuid) {
        if (productRepository.existsByUuid(uuid)) {
            productRepository.deleteByUuid(uuid);
            return true;
        }

        return false;
    }
}