package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByNameIn(List<String> tags);

}
