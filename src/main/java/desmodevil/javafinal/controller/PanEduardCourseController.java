package desmodevil.javafinal.controller;

import desmodevil.javafinal.dto.course.PanEduardCourseRequestDto;
import desmodevil.javafinal.dto.course.PanEduardCourseResponseDto;
import desmodevil.javafinal.service.PanEduardCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class PanEduardCourseController {

    private final PanEduardCourseService courseService;

    @PostMapping
    public ResponseEntity<PanEduardCourseResponseDto> createCourse(
            @Valid @RequestBody PanEduardCourseRequestDto requestDto
    ) {
        PanEduardCourseResponseDto responseDto = courseService.createCourse(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanEduardCourseResponseDto> getCourseById(
            @PathVariable Long id
    ) {
        PanEduardCourseResponseDto responseDto = courseService.getCourseById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PanEduardCourseResponseDto>> getCourses(
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long instructorId
    ) {
        List<PanEduardCourseResponseDto> courses = courseService.getCourses(departmentId, instructorId);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanEduardCourseResponseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody PanEduardCourseRequestDto requestDto
    ) {
        PanEduardCourseResponseDto responseDto = courseService.updateCourse(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id
    ) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}