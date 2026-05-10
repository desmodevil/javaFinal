package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardEnrollment;
import desmodevil.javafinal.enums.PanEduardEnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanEduardEnrollmentRepository extends JpaRepository<PanEduardEnrollment, Long> {

    List<PanEduardEnrollment> findByStudentId(Long studentId);

    List<PanEduardEnrollment> findByCourseId(Long courseId);

    List<PanEduardEnrollment> findByStatus(PanEduardEnrollmentStatus status);

    List<PanEduardEnrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<PanEduardEnrollment> findByStudentIdAndStatus(Long studentId, PanEduardEnrollmentStatus status);

    List<PanEduardEnrollment> findByCourseIdAndStatus(Long courseId, PanEduardEnrollmentStatus status);

    List<PanEduardEnrollment> findByStudentIdAndCourseIdAndStatus(
            Long studentId,
            Long courseId,
            PanEduardEnrollmentStatus status
    );

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}