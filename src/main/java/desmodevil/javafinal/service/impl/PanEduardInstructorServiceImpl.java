package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.instructor.PanEduardInstructorRequestDto;
import desmodevil.javafinal.dto.instructor.PanEduardInstructorResponseDto;
import desmodevil.javafinal.entity.PanEduardDepartment;
import desmodevil.javafinal.entity.PanEduardInstructor;
import desmodevil.javafinal.exception.PanEduardResourceNotFoundException;
import desmodevil.javafinal.mapper.PanEduardInstructorMapper;
import desmodevil.javafinal.repository.PanEduardDepartmentRepository;
import desmodevil.javafinal.repository.PanEduardInstructorRepository;
import desmodevil.javafinal.service.PanEduardInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PanEduardInstructorServiceImpl implements PanEduardInstructorService {

    private final PanEduardInstructorRepository instructorRepository;
    private final PanEduardDepartmentRepository departmentRepository;
    private final PanEduardInstructorMapper instructorMapper;

    @Override
    @Transactional
    public PanEduardInstructorResponseDto createInstructor(PanEduardInstructorRequestDto requestDto) {
        if (instructorRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Instructor with this email already exists");
        }

        PanEduardDepartment department = findDepartmentEntityById(requestDto.getDepartmentId());

        PanEduardInstructor instructor = instructorMapper.toEntity(requestDto, department);
        PanEduardInstructor savedInstructor = instructorRepository.save(instructor);

        return instructorMapper.toResponseDto(savedInstructor);
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardInstructorResponseDto getInstructorById(Long id) {
        PanEduardInstructor instructor = findInstructorEntityById(id);
        return instructorMapper.toResponseDto(instructor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanEduardInstructorResponseDto> getAllInstructors() {
        return instructorRepository.findAll()
                .stream()
                .map(instructorMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PanEduardInstructorResponseDto updateInstructor(
            Long id,
            PanEduardInstructorRequestDto requestDto
    ) {
        PanEduardInstructor instructor = findInstructorEntityById(id);

        if (instructorRepository.existsByEmailAndIdNot(requestDto.getEmail(), id)) {
            throw new IllegalArgumentException("Instructor with this email already exists");
        }

        PanEduardDepartment department = findDepartmentEntityById(requestDto.getDepartmentId());

        instructorMapper.updateEntity(instructor, requestDto, department);
        PanEduardInstructor updatedInstructor = instructorRepository.save(instructor);

        return instructorMapper.toResponseDto(updatedInstructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(Long id) {
        PanEduardInstructor instructor = findInstructorEntityById(id);
        instructorRepository.delete(instructor);
    }

    private PanEduardInstructor findInstructorEntityById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Instructor not found with id: " + id
                ));
    }

    private PanEduardDepartment findDepartmentEntityById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Department not found with id: " + id
                ));
    }
}