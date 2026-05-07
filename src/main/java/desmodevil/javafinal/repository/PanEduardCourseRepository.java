package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanEduardCourseRepository extends JpaRepository<PanEduardCourse, Long> {

    Optional<PanEduardCourse> findByCode(String code);

    boolean existsByCode(String code);
}
