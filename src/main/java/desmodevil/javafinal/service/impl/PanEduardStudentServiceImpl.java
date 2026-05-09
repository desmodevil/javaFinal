package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.student.PanEduardStudentRequestDto;
import desmodevil.javafinal.dto.student.PanEduardStudentResponseDto;
import desmodevil.javafinal.entity.PanEduardDepartment;
import desmodevil.javafinal.entity.PanEduardStudent;
import desmodevil.javafinal.exception.PanEduardResourceNotFoundException;
import desmodevil.javafinal.mapper.PanEduardStudentMapper;
import desmodevil.javafinal.repository.PanEduardDepartmentRepository;
import desmodevil.javafinal.repository.PanEduardStudentRepository;
import desmodevil.javafinal.service.PanEduardStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PanEduardStudentServiceImpl implements PanEduardStudentService {

    private final PanEduardStudentRepository studentRepository;
    private final PanEduardDepartmentRepository departmentRepository;
    private final PanEduardStudentMapper studentMapper;

    @Override
    @Transactional
    public PanEduardStudentResponseDto createStudent(PanEduardStudentRequestDto requestDto) {
        if (studentRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Student with this email already exists");
        }

        PanEduardDepartment department = findDepartmentEntityById(requestDto.getDepartmentId());

        PanEduardStudent student = studentMapper.toEntity(requestDto, department);
        PanEduardStudent savedStudent = studentRepository.save(student);

        return studentMapper.toResponseDto(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardStudentResponseDto getStudentById(Long id) {
        PanEduardStudent student = findStudentEntityById(id);
        return studentMapper.toResponseDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanEduardStudentResponseDto> getStudents(Long departmentId) {
        List<PanEduardStudent> students;

        if (departmentId == null) {
            students = studentRepository.findAll();
        } else {
            students = studentRepository.findByDepartmentId(departmentId);
        }

        return students.stream()
                .map(studentMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PanEduardStudentResponseDto updateStudent(Long id, PanEduardStudentRequestDto requestDto) {
        PanEduardStudent student = findStudentEntityById(id);

        if (studentRepository.existsByEmailAndIdNot(requestDto.getEmail(), id)) {
            throw new IllegalArgumentException("Student with this email already exists");
        }

        PanEduardDepartment department = findDepartmentEntityById(requestDto.getDepartmentId());

        studentMapper.updateEntity(student, requestDto, department);
        PanEduardStudent updatedStudent = studentRepository.save(student);

        return studentMapper.toResponseDto(updatedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        PanEduardStudent student = findStudentEntityById(id);
        studentRepository.delete(student);
    }

    private PanEduardStudent findStudentEntityById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Student not found with id: " + id
                ));
    }

    private PanEduardDepartment findDepartmentEntityById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Department not found with id: " + id
                ));
    }
}