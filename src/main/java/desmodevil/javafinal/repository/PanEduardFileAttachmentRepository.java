package desmodevil.javafinal.repository;

import desmodevil.javafinal.entity.PanEduardFileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanEduardFileAttachmentRepository extends JpaRepository<PanEduardFileAttachment, Long> {

    List<PanEduardFileAttachment> findByCourseId(Long courseId);
}