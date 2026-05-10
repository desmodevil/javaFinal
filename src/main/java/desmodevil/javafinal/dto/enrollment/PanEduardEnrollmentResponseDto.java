package desmodevil.javafinal.dto.enrollment;

import desmodevil.javafinal.enums.PanEduardEnrollmentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PanEduardEnrollmentResponseDto {

    private Long id;

    private Long studentId;

    private String studentFullName;

    private Long courseId;

    private String courseTitle;

    private String courseCode;

    private PanEduardEnrollmentStatus status;

    private LocalDateTime enrolledAt;

    private String grade;
}