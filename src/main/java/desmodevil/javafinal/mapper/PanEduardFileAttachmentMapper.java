package desmodevil.javafinal.mapper;

import desmodevil.javafinal.dto.file.PanEduardFileResponseDto;
import desmodevil.javafinal.entity.PanEduardCourse;
import desmodevil.javafinal.entity.PanEduardFileAttachment;
import org.springframework.stereotype.Component;

@Component
public class PanEduardFileAttachmentMapper {

    public PanEduardFileResponseDto toResponseDto(PanEduardFileAttachment fileAttachment) {
        PanEduardCourse course = fileAttachment.getCourse();

        return PanEduardFileResponseDto.builder()
                .id(fileAttachment.getId())
                .originalFileName(fileAttachment.getOriginalFileName())
                .contentType(fileAttachment.getContentType())
                .size(fileAttachment.getSize())
                .uploadedAt(fileAttachment.getUploadedAt())
                .courseId(course != null ? course.getId() : null)
                .courseTitle(course != null ? course.getTitle() : null)
                .build();
    }
}