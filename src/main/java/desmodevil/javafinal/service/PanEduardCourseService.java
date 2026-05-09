package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.course.PanEduardCourseRequestDto;
import desmodevil.javafinal.dto.course.PanEduardCourseResponseDto;

import java.util.List;

public interface PanEduardCourseService {

    PanEduardCourseResponseDto createCourse(PanEduardCourseRequestDto requestDto);

    PanEduardCourseResponseDto getCourseById(Long id);

    List<PanEduardCourseResponseDto> getCourses(Long departmentId, Long instructorId);

    PanEduardCourseResponseDto updateCourse(Long id, PanEduardCourseRequestDto requestDto);

    void deleteCourse(Long id);
}