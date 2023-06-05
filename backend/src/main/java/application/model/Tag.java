package application.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Tag {
    @Id
    @Column(columnDefinition = "uuid", unique = true, nullable = false)
    private String id;

    private String name;

    @PrePersist
    private void generateUuid() {
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
    }

    @ManyToMany(mappedBy = "tags")
    private List<Product> products;

    public Tag(String id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Tag() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
