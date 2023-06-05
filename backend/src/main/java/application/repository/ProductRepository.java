package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import application.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p.uuid FROM Product p JOIN p.tags t WHERE t.name IN (:tagNames)")
    List<String> findUuidsByTagNames(@Param("tagNames") List<String> tags);    

    Product findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    void deleteByUuid(String uuid);

}
