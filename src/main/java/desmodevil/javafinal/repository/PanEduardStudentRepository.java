package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PanEduardStudentRepository extends JpaRepository<PanEduardStudent, Long> {

    Optional<PanEduardStudent> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<PanEduardStudent> findByDepartmentId(Long departmentId);
}