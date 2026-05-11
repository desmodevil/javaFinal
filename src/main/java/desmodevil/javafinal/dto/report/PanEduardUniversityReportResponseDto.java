package desmodevil.javafinal.dto.report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PanEduardUniversityReportResponseDto {

    private LocalDateTime generatedAt;

    private long universitiesCount;

    private long departmentsCount;

    private long instructorsCount;

    private long studentsCount;

    private long coursesCount;

    private long enrollmentsCount;

    private long filesCount;
}