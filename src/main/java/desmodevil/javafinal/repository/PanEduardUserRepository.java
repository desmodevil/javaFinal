package desmodevil.javafinal.repository;


import desmodevil.javafinal.entity.PanEduardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanEduardUserRepository extends JpaRepository<PanEduardUser, Long> {

    Optional<PanEduardUser> findByUsername(String username);

    Optional<PanEduardUser> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}