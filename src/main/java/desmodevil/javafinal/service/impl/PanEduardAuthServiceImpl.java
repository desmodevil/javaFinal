package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.auth.PanEduardAuthResponseDto;
import desmodevil.javafinal.dto.auth.PanEduardLoginRequestDto;
import desmodevil.javafinal.dto.auth.PanEduardRegisterRequestDto;
import desmodevil.javafinal.entity.PanEduardUser;
import desmodevil.javafinal.enums.PanEduardRole;
import desmodevil.javafinal.exception.PanEduardResourceNotFoundException;
import desmodevil.javafinal.repository.PanEduardUserRepository;
import desmodevil.javafinal.security.PanEduardJwtService;
import desmodevil.javafinal.security.PanEduardUserDetailsService;
import desmodevil.javafinal.service.PanEduardAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PanEduardAuthServiceImpl implements PanEduardAuthService {

    private final PanEduardUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PanEduardUserDetailsService userDetailsService;
    private final PanEduardJwtService jwtService;

    @Override
    @Transactional
    public PanEduardAuthResponseDto register(PanEduardRegisterRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalArgumentException("User with this username already exists");
        }

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        PanEduardRole role = requestDto.getRole() == null
                ? PanEduardRole.STUDENT
                : requestDto.getRole();

        PanEduardUser user = PanEduardUser.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(role)
                .enabled(true)
                .build();

        PanEduardUser savedUser = userRepository.save(user);

        log.info(
                "User registered successfully: userId={}, username={}, role={}",
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole()
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUsername());
        String token = jwtService.generateToken(userDetails);

        return buildAuthResponse(savedUser, token);
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardAuthResponseDto login(PanEduardLoginRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUsernameOrEmail(),
                        requestDto.getPassword()
                )
        );

        log.info("User logged in successfully: usernameOrEmail={}", requestDto.getUsernameOrEmail());

        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUsernameOrEmail());
        String token = jwtService.generateToken(userDetails);

        PanEduardUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "User not found with username: " + userDetails.getUsername()
                ));

        return buildAuthResponse(user, token);
    }

    private PanEduardAuthResponseDto buildAuthResponse(PanEduardUser user, String token) {
        return PanEduardAuthResponseDto.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}