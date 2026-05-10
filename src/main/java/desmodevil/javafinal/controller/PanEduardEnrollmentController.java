package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentRequestDto;
import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentResponseDto;
import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentUpdateDto;
import desmodevil.javafinal.enums.PanEduardEnrollmentStatus;
import desmodevil.javafinal.service.PanEduardEnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class PanEduardEnrollmentController {

    private final PanEduardEnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<PanEduardEnrollmentResponseDto> createEnrollment(
            @Valid @RequestBody PanEduardEnrollmentRequestDto requestDto
    ) {
        PanEduardEnrollmentResponseDto responseDto = enrollmentService.createEnrollment(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanEduardEnrollmentResponseDto> getEnrollmentById(
            @PathVariable Long id
    ) {
        PanEduardEnrollmentResponseDto responseDto = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PanEduardEnrollmentResponseDto>> getEnrollments(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) PanEduardEnrollmentStatus status
    ) {
        List<PanEduardEnrollmentResponseDto> enrollments =
                enrollmentService.getEnrollments(studentId, courseId, status);

        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanEduardEnrollmentResponseDto> updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody PanEduardEnrollmentUpdateDto updateDto
    ) {
        PanEduardEnrollmentResponseDto responseDto =
                enrollmentService.updateEnrollment(id, updateDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable Long id
    ) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}