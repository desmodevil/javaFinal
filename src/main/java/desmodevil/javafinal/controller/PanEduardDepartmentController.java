package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.common.PanEduardPageResponseDto;
import desmodevil.javafinal.dto.department.PanEduardDepartmentRequestDto;
import desmodevil.javafinal.dto.department.PanEduardDepartmentResponseDto;
import desmodevil.javafinal.service.PanEduardDepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class PanEduardDepartmentController {

    private final PanEduardDepartmentService departmentService;

    @PostMapping
    public ResponseEntity<PanEduardDepartmentResponseDto> createDepartment(
            @Valid @RequestBody PanEduardDepartmentRequestDto requestDto
    ) {
        PanEduardDepartmentResponseDto responseDto = departmentService.createDepartment(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanEduardDepartmentResponseDto> getDepartmentById(
            @PathVariable Long id
    ) {
        PanEduardDepartmentResponseDto responseDto = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<PanEduardPageResponseDto<PanEduardDepartmentResponseDto>> getDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long universityId
    ) {
        PanEduardPageResponseDto<PanEduardDepartmentResponseDto> responseDto =
                departmentService.getDepartments(page, size, sortBy, sortDir, search, universityId);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanEduardDepartmentResponseDto> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody PanEduardDepartmentRequestDto requestDto
    ) {
        PanEduardDepartmentResponseDto responseDto = departmentService.updateDepartment(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id
    ) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}