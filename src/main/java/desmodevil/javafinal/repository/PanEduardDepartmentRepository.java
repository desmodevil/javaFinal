package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PanEduardDepartmentRepository extends JpaRepository<PanEduardDepartment, Long> {

    Optional<PanEduardDepartment> findByCode(String code);

    boolean existsByCode(String code);

    boolean existsByName(String name);

    boolean existsByCodeAndIdNot(String code, Long id);

    boolean existsByNameAndIdNot(String name, Long id);

    @Query("SELECT d FROM PanEduardDepartment d " +
            "WHERE (:search IS NULL OR :search = '' OR " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.code) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:universityId IS NULL OR d.university.id = :universityId)")
    Page<PanEduardDepartment> searchDepartments(
            @Param("search") String search,
            @Param("universityId") Long universityId,
            Pageable pageable
    );
}