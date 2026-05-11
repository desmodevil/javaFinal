package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.file.PanEduardFileResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PanEduardFileAttachmentService {

    PanEduardFileResponseDto uploadFile(Long courseId, MultipartFile file);

    Resource downloadFile(Long fileId);

    PanEduardFileResponseDto getFileById(Long fileId);

    List<PanEduardFileResponseDto> getFiles(Long courseId);

    void deleteFile(Long fileId);
}