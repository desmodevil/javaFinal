package desmodevil.javafinal.dto.instructor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PanEduardInstructorResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String academicTitle;

    private Long departmentId;

    private String departmentName;
}