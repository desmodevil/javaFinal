package desmodevil.javafinal.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PanEduardDepartmentRequestDto {

    @NotBlank(message = "Department name is required")
    @Size(max = 120, message = "Department name must be less than 120 characters")
    private String name;

    @NotBlank(message = "Department code is required")
    @Size(max = 20, message = "Department code must be less than 20 characters")
    private String code;

    @NotNull(message = "University id is required")
    private Long universityId;
}