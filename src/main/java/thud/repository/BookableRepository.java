package thud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import thud.entity.Bookable;

public interface BookableRepository extends JpaRepository<Bookable, Long> {
    List<Bookable> findByTitleContaining(String title);
}
