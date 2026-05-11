package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.file.PanEduardFileResponseDto;
import desmodevil.javafinal.entity.PanEduardCourse;
import desmodevil.javafinal.entity.PanEduardFileAttachment;
import desmodevil.javafinal.exception.PanEduardResourceNotFoundException;
import desmodevil.javafinal.mapper.PanEduardFileAttachmentMapper;
import desmodevil.javafinal.repository.PanEduardCourseRepository;
import desmodevil.javafinal.repository.PanEduardFileAttachmentRepository;
import desmodevil.javafinal.service.PanEduardFileAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import desmodevil.javafinal.service.PanEduardAsyncProcessService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PanEduardFileAttachmentServiceImpl implements PanEduardFileAttachmentService {

    private final PanEduardFileAttachmentRepository fileAttachmentRepository;
    private final PanEduardCourseRepository courseRepository;
    private final PanEduardFileAttachmentMapper fileAttachmentMapper;
    private final PanEduardAsyncProcessService asyncProcessService;


    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public PanEduardFileResponseDto uploadFile(Long courseId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        PanEduardCourse course = findCourseEntityById(courseId);

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            String originalFileName = StringUtils.cleanPath(
                    file.getOriginalFilename() == null ? "unknown-file" : file.getOriginalFilename()
            );

            if (originalFileName.contains("..")) {
                throw new IllegalArgumentException("Invalid file name");
            }

            String storedFileName = UUID.randomUUID() + "_" + originalFileName;
            Path targetLocation = uploadPath.resolve(storedFileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String contentType = file.getContentType() == null
                    ? "application/octet-stream"
                    : file.getContentType();

            PanEduardFileAttachment fileAttachment = PanEduardFileAttachment.builder()
                    .originalFileName(originalFileName)
                    .storedFileName(storedFileName)
                    .contentType(contentType)
                    .size(file.getSize())
                    .storagePath(targetLocation.toString())
                    .course(course)
                    .build();

            PanEduardFileAttachment savedFileAttachment =
                    fileAttachmentRepository.save(fileAttachment);
            asyncProcessService.processUploadedFile(
                    savedFileAttachment.getId(),
                    savedFileAttachment.getOriginalFileName(),
                    savedFileAttachment.getSize()
            );

            return fileAttachmentMapper.toResponseDto(savedFileAttachment);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not upload file", exception);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Resource downloadFile(Long fileId) {
        PanEduardFileAttachment fileAttachment = findFileAttachmentEntityById(fileId);

        try {
            Path filePath = Paths.get(fileAttachment.getStoragePath()).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new PanEduardResourceNotFoundException("File not found on disk with id: " + fileId);
            }

            return resource;
        } catch (MalformedURLException exception) {
            throw new IllegalStateException("Could not read file", exception);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardFileResponseDto getFileById(Long fileId) {
        PanEduardFileAttachment fileAttachment = findFileAttachmentEntityById(fileId);
        return fileAttachmentMapper.toResponseDto(fileAttachment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanEduardFileResponseDto> getFiles(Long courseId) {
        List<PanEduardFileAttachment> files;

        if (courseId == null) {
            files = fileAttachmentRepository.findAll();
        } else {
            files = fileAttachmentRepository.findByCourseId(courseId);
        }

        return files.stream()
                .map(fileAttachmentMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteFile(Long fileId) {
        PanEduardFileAttachment fileAttachment = findFileAttachmentEntityById(fileId);

        try {
            Path filePath = Paths.get(fileAttachment.getStoragePath()).normalize();
            Files.deleteIfExists(filePath);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not delete file from disk", exception);
        }

        fileAttachmentRepository.delete(fileAttachment);
    }

    private PanEduardFileAttachment findFileAttachmentEntityById(Long id) {
        return fileAttachmentRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "File attachment not found with id: " + id
                ));
    }

    private PanEduardCourse findCourseEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Course not found with id: " + id
                ));
    }
}