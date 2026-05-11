package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.report.PanEduardUniversityReportResponseDto;
import desmodevil.javafinal.service.PanEduardAsyncProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class PanEduardReportController {

    private final PanEduardAsyncProcessService asyncProcessService;

    @GetMapping("/university-summary")
    @PreAuthorize("hasRole('ADMIN')")
    public CompletableFuture<ResponseEntity<PanEduardUniversityReportResponseDto>> generateUniversitySummaryReport() {
        return asyncProcessService.generateUniversityReport()
                .thenApply(ResponseEntity::ok);
    }
}