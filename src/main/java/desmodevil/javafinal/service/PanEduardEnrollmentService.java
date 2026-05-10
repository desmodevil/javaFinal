package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentRequestDto;
import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentResponseDto;
import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentUpdateDto;
import desmodevil.javafinal.enums.PanEduardEnrollmentStatus;

import java.util.List;

public interface PanEduardEnrollmentService {

    PanEduardEnrollmentResponseDto createEnrollment(PanEduardEnrollmentRequestDto requestDto);

    PanEduardEnrollmentResponseDto getEnrollmentById(Long id);

    List<PanEduardEnrollmentResponseDto> getEnrollments(
            Long studentId,
            Long courseId,
            PanEduardEnrollmentStatus status
    );

    PanEduardEnrollmentResponseDto updateEnrollment(Long id, PanEduardEnrollmentUpdateDto updateDto);

    void deleteEnrollment(Long id);
}