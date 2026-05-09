package desmodevil.javafinal.dto.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PanEduardCourseRequestDto {

    @NotBlank(message = "Course title is required")
    @Size(max = 160, message = "Course title must be less than 160 characters")
    private String title;

    @NotBlank(message = "Course code is required")
    @Size(max = 30, message = "Course code must be less than 30 characters")
    private String code;

    @NotNull(message = "Credits are required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits must be less than or equal to 10")
    private Integer credits;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;

    @NotNull(message = "Department id is required")
    private Long departmentId;

    @NotNull(message = "Instructor id is required")
    private Long instructorId;
}