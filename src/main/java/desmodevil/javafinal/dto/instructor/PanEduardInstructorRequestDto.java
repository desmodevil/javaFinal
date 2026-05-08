package desmodevil.javafinal.dto.instructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PanEduardInstructorRequestDto {

    @NotBlank(message = "First name is required")
    @Size(max = 80, message = "First name must be less than 80 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 80, message = "Last name must be less than 80 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 120, message = "Email must be less than 120 characters")
    private String email;

    @Size(max = 120, message = "Academic title must be less than 120 characters")
    private String academicTitle;

    @NotNull(message = "Department id is required")
    private Long departmentId;
}