package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.file.PanEduardFileResponseDto;
import desmodevil.javafinal.service.PanEduardFileAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class PanEduardFileAttachmentController {

    private final PanEduardFileAttachmentService fileAttachmentService;

    @PostMapping("/courses/{courseId}/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PanEduardFileResponseDto> uploadFile(
            @PathVariable Long courseId,
            @RequestParam("file") MultipartFile file
    ) {
        PanEduardFileResponseDto responseDto =
                fileAttachmentService.uploadFile(courseId, file);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{fileId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public ResponseEntity<PanEduardFileResponseDto> getFileById(
            @PathVariable Long fileId
    ) {
        PanEduardFileResponseDto responseDto =
                fileAttachmentService.getFileById(fileId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public ResponseEntity<List<PanEduardFileResponseDto>> getFiles(
            @RequestParam(required = false) Long courseId
    ) {
        List<PanEduardFileResponseDto> files =
                fileAttachmentService.getFiles(courseId);

        return ResponseEntity.ok(files);
    }

    @GetMapping("/{fileId}/download")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable Long fileId
    ) {
        PanEduardFileResponseDto fileInfo =
                fileAttachmentService.getFileById(fileId);

        Resource resource =
                fileAttachmentService.downloadFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileInfo.getContentType()))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileInfo.getOriginalFileName() + "\""
                )
                .body(resource);
    }

    @DeleteMapping("/{fileId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFile(
            @PathVariable Long fileId
    ) {
        fileAttachmentService.deleteFile(fileId);
        return ResponseEntity.noContent().build();
    }
}