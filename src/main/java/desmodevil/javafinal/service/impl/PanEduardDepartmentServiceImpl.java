package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.common.PanEduardPageResponseDto;
import desmodevil.javafinal.dto.department.PanEduardDepartmentRequestDto;
import desmodevil.javafinal.dto.department.PanEduardDepartmentResponseDto;
import desmodevil.javafinal.entity.PanEduardDepartment;
import desmodevil.javafinal.entity.PanEduardUniversity;
import desmodevil.javafinal.exception.PanEduardResourceNotFoundException;
import desmodevil.javafinal.mapper.PanEduardDepartmentMapper;
import desmodevil.javafinal.repository.PanEduardDepartmentRepository;
import desmodevil.javafinal.repository.PanEduardUniversityRepository;
import desmodevil.javafinal.service.PanEduardDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PanEduardDepartmentServiceImpl implements PanEduardDepartmentService {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "name", "code");

    private final PanEduardDepartmentRepository departmentRepository;
    private final PanEduardUniversityRepository universityRepository;
    private final PanEduardDepartmentMapper departmentMapper;

    @Override
    @Transactional
    public PanEduardDepartmentResponseDto createDepartment(PanEduardDepartmentRequestDto requestDto) {
        if (departmentRepository.existsByName(requestDto.getName())) {
            throw new IllegalArgumentException("Department with this name already exists");
        }

        if (departmentRepository.existsByCode(requestDto.getCode())) {
            throw new IllegalArgumentException("Department with this code already exists");
        }

        PanEduardUniversity university = findUniversityEntityById(requestDto.getUniversityId());

        PanEduardDepartment department = departmentMapper.toEntity(requestDto, university);
        PanEduardDepartment savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponseDto(savedDepartment);
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardDepartmentResponseDto getDepartmentById(Long id) {
        PanEduardDepartment department = findDepartmentEntityById(id);
        return departmentMapper.toResponseDto(department);
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardPageResponseDto<PanEduardDepartmentResponseDto> getDepartments(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String search,
            Long universityId
    ) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException("Page size must be between 1 and 100");
        }

        String normalizedSortBy = sortBy == null || sortBy.isBlank() ? "id" : sortBy;

        if (!ALLOWED_SORT_FIELDS.contains(normalizedSortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + normalizedSortBy);
        }

        Sort.Direction direction = Sort.Direction.fromOptionalString(sortDir)
                .orElse(Sort.Direction.ASC);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, normalizedSortBy)
        );

        Page<PanEduardDepartment> departmentPage = departmentRepository.searchDepartments(
                search,
                universityId,
                pageable
        );

        List<PanEduardDepartmentResponseDto> content = departmentPage.getContent()
                .stream()
                .map(departmentMapper::toResponseDto)
                .toList();

        return PanEduardPageResponseDto.<PanEduardDepartmentResponseDto>builder()
                .content(content)
                .pageNumber(departmentPage.getNumber())
                .pageSize(departmentPage.getSize())
                .totalElements(departmentPage.getTotalElements())
                .totalPages(departmentPage.getTotalPages())
                .first(departmentPage.isFirst())
                .last(departmentPage.isLast())
                .build();
    }

    @Override
    @Transactional
    public PanEduardDepartmentResponseDto updateDepartment(
            Long id,
            PanEduardDepartmentRequestDto requestDto
    ) {
        PanEduardDepartment department = findDepartmentEntityById(id);

        if (departmentRepository.existsByNameAndIdNot(requestDto.getName(), id)) {
            throw new IllegalArgumentException("Department with this name already exists");
        }

        if (departmentRepository.existsByCodeAndIdNot(requestDto.getCode(), id)) {
            throw new IllegalArgumentException("Department with this code already exists");
        }

        PanEduardUniversity university = findUniversityEntityById(requestDto.getUniversityId());

        departmentMapper.updateEntity(department, requestDto, university);
        PanEduardDepartment updatedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponseDto(updatedDepartment);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        PanEduardDepartment department = findDepartmentEntityById(id);
        departmentRepository.delete(department);
    }

    private PanEduardDepartment findDepartmentEntityById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Department not found with id: " + id
                ));
    }

    private PanEduardUniversity findUniversityEntityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "University not found with id: " + id
                ));
    }
}