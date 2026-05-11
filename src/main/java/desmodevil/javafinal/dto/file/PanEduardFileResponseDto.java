package desmodevil.javafinal.dto.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PanEduardFileResponseDto {

    private Long id;

    private String originalFileName;

    private String contentType;

    private Long size;

    private LocalDateTime uploadedAt;

    private Long courseId;

    private String courseTitle;
}