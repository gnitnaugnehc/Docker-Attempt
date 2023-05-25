package application;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import application.model.Product;
import application.model.Tag;
import application.repository.ProductRepository;
import application.repository.TagRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner (ProductRepository productRepository, TagRepository tagRepository) {
        return args -> {
            Tag tag1 = new Tag();
            tag1.setName("Electronics");
            Tag tag2 = new Tag();
            tag2.setName("Clothing");
            Tag tag3 = new Tag();
            tag3.setName("Accessories");

            tagRepository.saveAll(List.of(tag1, tag2, tag3));

            Product product1 = new Product();
            product1.setName("Laptop");
            product1.setDescription("High-performance laptop");
            product1.setPrice(1000.0);
            product1.setTags(List.of(tag1, tag3));

            Product product2 = new Product();
            product2.setName("T-Shirt");
            product2.setDescription("Casual t-shirt");
            product2.setPrice(20.0);
            product2.setTags(List.of(tag2));

            Product product3 = new Product();
            product3.setName("Headphones");
            product3.setDescription("Wireless headphones");
            product3.setPrice(150.0);
            product3.setTags(List.of(tag1, tag3));

            productRepository.saveAll(List.of(product1, product2, product3));
        };
    }

}
