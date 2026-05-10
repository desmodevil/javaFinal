package desmodevil.javafinal.dto.enrollment;

import desmodevil.javafinal.enums.PanEduardEnrollmentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PanEduardEnrollmentUpdateDto {

    @NotNull(message = "Enrollment status is required")
    private PanEduardEnrollmentStatus status;

    @Size(max = 5, message = "Grade must be less than 5 characters")
    private String grade;
}