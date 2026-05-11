package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.report.PanEduardUniversityReportResponseDto;

import java.util.concurrent.CompletableFuture;

public interface PanEduardAsyncProcessService {

    CompletableFuture<Void> sendEnrollmentNotification(
            Long studentId,
            String studentEmail,
            String courseCode
    );

    CompletableFuture<Void> processUploadedFile(
            Long fileId,
            String originalFileName,
            Long fileSize
    );

    CompletableFuture<PanEduardUniversityReportResponseDto> generateUniversityReport();
}