package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.course.PanEduardCourseRequestDto;
import desmodevil.javafinal.dto.course.PanEduardCourseResponseDto;
import desmodevil.javafinal.entity.PanEduardCourse;
import desmodevil.javafinal.entity.PanEduardDepartment;
import desmodevil.javafinal.entity.PanEduardInstructor;
import desmodevil.javafinal.exception.PanEduardResourceNotFoundException;
import desmodevil.javafinal.mapper.PanEduardCourseMapper;
import desmodevil.javafinal.repository.PanEduardCourseRepository;
import desmodevil.javafinal.repository.PanEduardDepartmentRepository;
import desmodevil.javafinal.repository.PanEduardInstructorRepository;
import desmodevil.javafinal.service.PanEduardCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PanEduardCourseServiceImpl implements PanEduardCourseService {

    private final PanEduardCourseRepository courseRepository;
    private final PanEduardDepartmentRepository departmentRepository;
    private final PanEduardInstructorRepository instructorRepository;
    private final PanEduardCourseMapper courseMapper;

    @Override
    @Transactional
    public PanEduardCourseResponseDto createCourse(PanEduardCourseRequestDto requestDto) {
        if (courseRepository.existsByCode(requestDto.getCode())) {
            throw new IllegalArgumentException("Course with this code already exists");
        }

        PanEduardDepartment department = findDepartmentEntityById(requestDto.getDepartmentId());
        PanEduardInstructor instructor = findInstructorEntityById(requestDto.getInstructorId());

        PanEduardCourse course = courseMapper.toEntity(requestDto, department, instructor);
        PanEduardCourse savedCourse = courseRepository.save(course);

        return courseMapper.toResponseDto(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardCourseResponseDto getCourseById(Long id) {
        PanEduardCourse course = findCourseEntityById(id);
        return courseMapper.toResponseDto(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanEduardCourseResponseDto> getCourses(Long departmentId, Long instructorId) {
        return courseRepository.findCoursesByFilters(departmentId, instructorId)
                .stream()
                .map(courseMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PanEduardCourseResponseDto updateCourse(Long id, PanEduardCourseRequestDto requestDto) {
        PanEduardCourse course = findCourseEntityById(id);

        if (courseRepository.existsByCodeAndIdNot(requestDto.getCode(), id)) {
            throw new IllegalArgumentException("Course with this code already exists");
        }

        PanEduardDepartment department = findDepartmentEntityById(requestDto.getDepartmentId());
        PanEduardInstructor instructor = findInstructorEntityById(requestDto.getInstructorId());

        courseMapper.updateEntity(course, requestDto, department, instructor);
        PanEduardCourse updatedCourse = courseRepository.save(course);

        return courseMapper.toResponseDto(updatedCourse);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        PanEduardCourse course = findCourseEntityById(id);
        courseRepository.delete(course);
    }

    private PanEduardCourse findCourseEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Course not found with id: " + id
                ));
    }

    private PanEduardDepartment findDepartmentEntityById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Department not found with id: " + id
                ));
    }

    private PanEduardInstructor findInstructorEntityById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "Instructor not found with id: " + id
                ));
    }
}