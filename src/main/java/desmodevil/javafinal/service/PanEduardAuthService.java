package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.auth.PanEduardAuthResponseDto;
import desmodevil.javafinal.dto.auth.PanEduardLoginRequestDto;
import desmodevil.javafinal.dto.auth.PanEduardRegisterRequestDto;

public interface PanEduardAuthService {

    PanEduardAuthResponseDto register(PanEduardRegisterRequestDto requestDto);

    PanEduardAuthResponseDto login(PanEduardLoginRequestDto requestDto);
}