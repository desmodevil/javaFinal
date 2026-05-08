package desmodevil.javafinal.dto.department;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PanEduardDepartmentResponseDto {

    private Long id;

    private String name;

    private String code;

    private Long universityId;

    private String universityName;
}