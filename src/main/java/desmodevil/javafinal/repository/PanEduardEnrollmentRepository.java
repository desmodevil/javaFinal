package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanEduardEnrollmentRepository extends JpaRepository<PanEduardEnrollment, Long> {

    List<PanEduardEnrollment> findByStudentId(Long studentId);

    List<PanEduardEnrollment> findByCourseId(Long courseId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}