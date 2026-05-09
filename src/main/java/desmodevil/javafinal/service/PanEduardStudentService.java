package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.student.PanEduardStudentRequestDto;
import desmodevil.javafinal.dto.student.PanEduardStudentResponseDto;

import java.util.List;

public interface PanEduardStudentService {

    PanEduardStudentResponseDto createStudent(PanEduardStudentRequestDto requestDto);

    PanEduardStudentResponseDto getStudentById(Long id);

    List<PanEduardStudentResponseDto> getStudents(Long departmentId);

    PanEduardStudentResponseDto updateStudent(Long id, PanEduardStudentRequestDto requestDto);

    void deleteStudent(Long id);
}