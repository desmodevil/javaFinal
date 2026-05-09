package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PanEduardCourseRepository extends JpaRepository<PanEduardCourse, Long> {

    Optional<PanEduardCourse> findByCode(String code);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, Long id);

    @Query("SELECT c FROM PanEduardCourse c " +
            "WHERE (:departmentId IS NULL OR c.department.id = :departmentId) " +
            "AND (:instructorId IS NULL OR c.instructor.id = :instructorId)")
    List<PanEduardCourse> findCoursesByFilters(
            @Param("departmentId") Long departmentId,
            @Param("instructorId") Long instructorId
    );
}