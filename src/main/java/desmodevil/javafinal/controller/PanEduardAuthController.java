package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.auth.PanEduardAuthResponseDto;
import desmodevil.javafinal.dto.auth.PanEduardLoginRequestDto;
import desmodevil.javafinal.dto.auth.PanEduardRegisterRequestDto;
import desmodevil.javafinal.service.PanEduardAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PanEduardAuthController {

    private final PanEduardAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<PanEduardAuthResponseDto> register(
            @Valid @RequestBody PanEduardRegisterRequestDto requestDto
    ) {
        PanEduardAuthResponseDto responseDto = authService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<PanEduardAuthResponseDto> login(
            @Valid @RequestBody PanEduardLoginRequestDto requestDto
    ) {
        PanEduardAuthResponseDto responseDto = authService.login(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}