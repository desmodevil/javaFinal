package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.instructor.PanEduardInstructorRequestDto;
import desmodevil.javafinal.dto.instructor.PanEduardInstructorResponseDto;
import desmodevil.javafinal.service.PanEduardInstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class PanEduardInstructorController {

    private final PanEduardInstructorService instructorService;

    @PostMapping
    public ResponseEntity<PanEduardInstructorResponseDto> createInstructor(
            @Valid @RequestBody PanEduardInstructorRequestDto requestDto
    ) {
        PanEduardInstructorResponseDto responseDto = instructorService.createInstructor(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanEduardInstructorResponseDto> getInstructorById(
            @PathVariable Long id
    ) {
        PanEduardInstructorResponseDto responseDto = instructorService.getInstructorById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PanEduardInstructorResponseDto>> getAllInstructors() {
        List<PanEduardInstructorResponseDto> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanEduardInstructorResponseDto> updateInstructor(
            @PathVariable Long id,
            @Valid @RequestBody PanEduardInstructorRequestDto requestDto
    ) {
        PanEduardInstructorResponseDto responseDto = instructorService.updateInstructor(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(
            @PathVariable Long id
    ) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}