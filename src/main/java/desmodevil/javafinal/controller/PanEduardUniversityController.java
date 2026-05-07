package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.university.PanEduardUniversityRequestDto;
import desmodevil.javafinal.dto.university.PanEduardUniversityResponseDto;
import desmodevil.javafinal.service.PanEduardUniversityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
@RequiredArgsConstructor
public class PanEduardUniversityController {

    private final PanEduardUniversityService universityService;

    @PostMapping
    public ResponseEntity<PanEduardUniversityResponseDto> createUniversity(
            @Valid @RequestBody PanEduardUniversityRequestDto requestDto
    ) {
        PanEduardUniversityResponseDto responseDto = universityService.createUniversity(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanEduardUniversityResponseDto> getUniversityById(
            @PathVariable Long id
    ) {
        PanEduardUniversityResponseDto responseDto = universityService.getUniversityById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PanEduardUniversityResponseDto>> getAllUniversities() {
        List<PanEduardUniversityResponseDto> universities = universityService.getAllUniversities();
        return ResponseEntity.ok(universities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanEduardUniversityResponseDto> updateUniversity(
            @PathVariable Long id,
            @Valid @RequestBody PanEduardUniversityRequestDto requestDto
    ) {
        PanEduardUniversityResponseDto responseDto = universityService.updateUniversity(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(
            @PathVariable Long id
    ) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }
}