package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.instructor.PanEduardInstructorRequestDto;
import desmodevil.javafinal.dto.instructor.PanEduardInstructorResponseDto;

import java.util.List;

public interface PanEduardInstructorService {

    PanEduardInstructorResponseDto createInstructor(PanEduardInstructorRequestDto requestDto);

    PanEduardInstructorResponseDto getInstructorById(Long id);

    List<PanEduardInstructorResponseDto> getAllInstructors();

    PanEduardInstructorResponseDto updateInstructor(Long id, PanEduardInstructorRequestDto requestDto);

    void deleteInstructor(Long id);
}