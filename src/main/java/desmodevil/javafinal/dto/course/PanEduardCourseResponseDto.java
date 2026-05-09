package desmodevil.javafinal.dto.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PanEduardCourseResponseDto {

    private Long id;

    private String title;

    private String code;

    private Integer credits;

    private String description;

    private Long departmentId;

    private String departmentName;

    private Long instructorId;

    private String instructorFullName;
}