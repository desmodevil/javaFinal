package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanEduardDepartmentRepository extends JpaRepository<PanEduardDepartment, Long> {

    Optional<PanEduardDepartment> findByCode(String code);

    boolean existsByCode(String code);

    boolean existsByName(String name);
}