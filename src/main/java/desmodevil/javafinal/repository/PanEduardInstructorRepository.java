package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardInstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanEduardInstructorRepository extends JpaRepository<PanEduardInstructor, Long> {

    Optional<PanEduardInstructor> findByEmail(String email);

    boolean existsByEmail(String email);
}