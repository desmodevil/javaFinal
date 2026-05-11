package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.report.PanEduardUniversityReportResponseDto;
import desmodevil.javafinal.repository.*;
import desmodevil.javafinal.service.PanEduardAsyncProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class PanEduardAsyncProcessServiceImpl implements PanEduardAsyncProcessService {

    private final PanEduardUniversityRepository universityRepository;
    private final PanEduardDepartmentRepository departmentRepository;
    private final PanEduardInstructorRepository instructorRepository;
    private final PanEduardStudentRepository studentRepository;
    private final PanEduardCourseRepository courseRepository;
    private final PanEduardEnrollmentRepository enrollmentRepository;
    private final PanEduardFileAttachmentRepository fileAttachmentRepository;

    @Override
    @Async("panEduardTaskExecutor")
    public CompletableFuture<Void> sendEnrollmentNotification(
            Long studentId,
            String studentEmail,
            String courseCode
    ) {
        log.info(
                "Async email notification started. studentId={}, email={}, courseCode={}",
                studentId,
                studentEmail,
                courseCode
        );

        simulateDelay();

        log.info(
                "Async email notification finished. Email sent to {} about course {}",
                studentEmail,
                courseCode
        );

        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async("panEduardTaskExecutor")
    public CompletableFuture<Void> processUploadedFile(
            Long fileId,
            String originalFileName,
            Long fileSize
    ) {
        log.info(
                "Async file processing started. fileId={}, fileName={}, size={}",
                fileId,
                originalFileName,
                fileSize
        );

        simulateDelay();

        log.info(
                "Async file processing finished. fileId={}, fileName={}",
                fileId,
                originalFileName
        );

        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async("panEduardTaskExecutor")
    public CompletableFuture<PanEduardUniversityReportResponseDto> generateUniversityReport() {
        log.info("Async university report generation started");

        simulateDelay();

        PanEduardUniversityReportResponseDto report = PanEduardUniversityReportResponseDto.builder()
                .generatedAt(LocalDateTime.now())
                .universitiesCount(universityRepository.count())
                .departmentsCount(departmentRepository.count())
                .instructorsCount(instructorRepository.count())
                .studentsCount(studentRepository.count())
                .coursesCount(courseRepository.count())
                .enrollmentsCount(enrollmentRepository.count())
                .filesCount(fileAttachmentRepository.count())
                .build();

        log.info("Async university report generation finished");

        return CompletableFuture.completedFuture(report);
    }

    private void simulateDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            log.warn("Async process was interrupted", exception);
        }
    }
}