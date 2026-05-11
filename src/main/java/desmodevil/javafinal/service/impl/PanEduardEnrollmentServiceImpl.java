package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentRequestDto;
import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentResponseDto;
import desmodevil.javafinal.dto.enrollment.PanEduardEnrollmentUpdateDto;
import desmodevil.javafinal.entity.PanEduardCourse;
import desmodevil.javafinal.entity.PanEduardEnrollment;
import desmodevil.javafinal.entity.PanEduardStudent;
import desmodevil.javafinal.enums.PanEduardEnrollmentStatus;
import desmodevil.javafinal.exception.PanEduardResourceNotFoundException;
import desmodevil.javafinal.mapper.PanEduardEnrollmentMapper;
import desmodevil.javafinal.repository.PanEduardCourseRepository;
import desmodevil.javafinal.repository.PanEduardEnrollmentRepository;
import desmodevil.javafinal.repository.PanEduardStudentRepository;
import desmodevil.javafinal.service.PanEduardAsyncProcessService;
import desmodevil.javafinal.service.PanEduardEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PanEduardEnrollmentServiceImpl implements PanEduardEnrollmentService {

    private final PanEduardEnrollmentRepository enrollmentRepository;
    private final PanEduardStudentRepository studentRepository;
    private final PanEduardCourseRepository courseRepository;
    private final PanEduardEnrollmentMapper enrollmentMapper;
    private final PanEduardAsyncProcessService asyncProcessService;

    @Override
    @Transactional
    public PanEduardEnrollmentResponseDto createEnrollment(PanEduardEnrollmentRequestDto requestDto) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(
                requestDto.getStudentId(),
                requestDto.getCourseId()
        )) {
            throw new IllegalArgumentException("Student is already enrolled in this course");
        }

        PanEduardStudent student = findStudentEntityById(requestDto.getStudentId());
        PanEduardCourse course = findCourseEntityById(requestDto.getCourseId());

        PanEduardEnrollment enrollment = enrollmentMapper.toEntity(requestDto, student, course);

        if (enrollment.getStatus() == null) {
            enrollment.setStatus(PanEduardEnrollmentStatus.ACTIVE);
        }

        PanEduardEnrollment savedEnrollment = enrollmentRepository.save(enrollment);
        asyncProcessService.sendEnrollmentNotification(
                savedEnrollment.getStudent().getId(),
                savedEnrollment.getStudent().getEmail(),
                savedEnrollment.getCourse().getCode()
        );

        return enrollmentMapper.toResponseDto(savedEnrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardEnrollmentResponseDto getEnrollmentById(Long id) {
        PanEduardEnrollment enrollment = findEnrollmentEntityById(id);
        return enrollmentMapper.toResponseDto(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanEduardEnrollmentResponseDto> getEnrollments(
            Long studentId,
            Long courseId,
            PanEduardEnrollmentStatus status
    ) {
        List<PanEduardEnrollment> enrollments;

        if (studentId != null && courseId != null && status != null) {
            enrollments = enrollmentRepository.findByStudentIdAndCourseIdAndStatus(studentId, courseId, status);
        } else if (studentId != null && courseId != null) {
            enrollments = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        } else if (studentId != null && status != null) {
            enrollments = enrollmentRepository.findByStudentIdAndStatus(studentId, status);
        } else if (courseId != null && status != null) {
            enrollments = enrollmentRepository.findByCourseIdAndStatus(courseId, status);
        } else if (studentId != null) {
            enrollments = enrollmentRepository.findByStudentId(studentId);
        } else if (courseId != null) {
            enrollments = enrollmentRepository.findByCourseId(courseId);
        } else if (status != null) {
            enrollments = enrollmentRepository.findByStatus(status);
        } else {
            enrollments = enrollmentRepository.findAll();
        }

        return enrollments.stream()
                .map(enrollmentMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PanEduardEnrollmentResponseDto updateEnrollment(
            Long id,
            PanEduardEnrollmentUpdateDto updateDto
    ) {
        PanEduardEnrollment enrollment = findEnrollmentEntityById(id);

        enrollmentMapper.updateEntity(enrollment, updateDto);

        PanEduardEnrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponseDto(updatedEnrollment);
    }

    @Override
    @Transactional
    public void deleteEnrollment(Long id) {
        PanEduardEnrollment enrollment = findEnrollmentEntityById(id);
        enrollmentRepository.delete(enrollment);
    }

    private PanEduardEnrollment findEnrollmentEntityById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Enrollment not found with id: " + id
                ));
    }

    private PanEduardStudent findStudentEntityById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Student not found with id: " + id
                ));
    }

    private PanEduardCourse findCourseEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Course not found with id: " + id
                ));
    }
}