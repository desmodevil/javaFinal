package desmodevil.javafinal.mapper;

import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentRequestDto;
import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentResponseDto;
import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentUpdateDto;
import desmodevil.javafinal.entity.PanEduardCourse;
import desmodevil.javafinal.entity.PanEduardEnrollment;
import desmodevil.javafinal.entity.PanEduardStudent;
import org.springframework.stereotype.Component;

@Component
public class PanEduardEnrollmentMapper {

    public PanEduardEnrollment toEntity(
            PanEduardEnrollmentRequestDto requestDto,
            PanEduardStudent student,
            PanEduardCourse course
    ) {
        return PanEduardEnrollment.builder()
                .student(student)
                .course(course)
                .status(requestDto.getStatus())
                .grade(requestDto.getGrade())
                .build();
    }

    public PanEduardEnrollmentResponseDto toResponseDto(PanEduardEnrollment enrollment) {
        PanEduardStudent student = enrollment.getStudent();
        PanEduardCourse course = enrollment.getCourse();

        String studentFullName = null;

        if (student != null) {
            studentFullName = student.getFirstName() + " " + student.getLastName();
        }

        return PanEduardEnrollmentResponseDto.builder()
                .id(enrollment.getId())
                .studentId(student != null ? student.getId() : null)
                .studentFullName(studentFullName)
                .courseId(course != null ? course.getId() : null)
                .courseTitle(course != null ? course.getTitle() : null)
                .courseCode(course != null ? course.getCode() : null)
                .status(enrollment.getStatus())
                .enrolledAt(enrollment.getEnrolledAt())
                .grade(enrollment.getGrade())
                .build();
    }

    public void updateEntity(
            PanEduardEnrollment enrollment,
            PanEduardEnrollmentUpdateDto updateDto
    ) {
        enrollment.setStatus(updateDto.getStatus());
        enrollment.setGrade(updateDto.getGrade());
    }
}