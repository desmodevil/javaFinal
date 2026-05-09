package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.student.PanEduardStudentRequestDto;
import desmodevil.javafinal.dto.student.PanEduardStudentResponseDto;
import desmodevil.javafinal.service.PanEduardStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class PanEduardStudentController {

    private final PanEduardStudentService studentService;

    @PostMapping
    public ResponseEntity<PanEduardStudentResponseDto> createStudent(
            @Valid @RequestBody PanEduardStudentRequestDto requestDto
    ) {
        PanEduardStudentResponseDto responseDto = studentService.createStudent(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanEduardStudentResponseDto> getStudentById(
            @PathVariable Long id
    ) {
        PanEduardStudentResponseDto responseDto = studentService.getStudentById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PanEduardStudentResponseDto>> getStudents(
            @RequestParam(required = false) Long departmentId
    ) {
        List<PanEduardStudentResponseDto> students = studentService.getStudents(departmentId);
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanEduardStudentResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody PanEduardStudentRequestDto requestDto
    ) {
        PanEduardStudentResponseDto responseDto = studentService.updateStudent(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id
    ) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}