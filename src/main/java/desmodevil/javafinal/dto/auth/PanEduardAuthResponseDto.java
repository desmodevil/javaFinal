package desmodevil.javafinal.dto.auth;

import desmodevil.javafinal.enums.PanEduardRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PanEduardAuthResponseDto {

    private String token;

    private String tokenType;

    private Long userId;

    private String username;

    private String email;

    private PanEduardRole role;
}