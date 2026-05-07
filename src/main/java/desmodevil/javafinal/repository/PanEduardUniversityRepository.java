package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardUniversity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanEduardUniversityRepository extends JpaRepository<PanEduardUniversity, Long> {

    Optional<PanEduardUniversity> findByCode(String code);

    boolean existsByCode(String code);

    boolean existsByName(String name);
}