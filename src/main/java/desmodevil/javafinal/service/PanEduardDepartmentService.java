package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.common.PanEduardPageResponseDto;
import desmodevil.javafinal.dto.department.PanEduardDepartmentRequestDto;
import desmodevil.javafinal.dto.department.PanEduardDepartmentResponseDto;

public interface PanEduardDepartmentService {

    PanEduardDepartmentResponseDto createDepartment(PanEduardDepartmentRequestDto requestDto);

    PanEduardDepartmentResponseDto getDepartmentById(Long id);

    PanEduardPageResponseDto<PanEduardDepartmentResponseDto> getDepartments(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String search,
            Long universityId
    );

    PanEduardDepartmentResponseDto updateDepartment(Long id, PanEduardDepartmentRequestDto requestDto);

    void deleteDepartment(Long id);
}