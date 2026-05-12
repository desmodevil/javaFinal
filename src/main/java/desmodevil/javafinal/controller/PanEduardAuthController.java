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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Registration and login endpoints")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PanEduardAuthController {

    private final PanEduardAuthService authService;

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<PanEduardAuthResponseDto> register(
            @Valid @RequestBody PanEduardRegisterRequestDto requestDto
    ) {
        PanEduardAuthResponseDto responseDto = authService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Login and receive JWT token")
    @PostMapping("/login")
    public ResponseEntity<PanEduardAuthResponseDto> login(
            @Valid @RequestBody PanEduardLoginRequestDto requestDto
    ) {
        PanEduardAuthResponseDto responseDto = authService.login(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}