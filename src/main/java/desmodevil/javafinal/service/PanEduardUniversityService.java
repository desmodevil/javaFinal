package desmodevil.javafinal.service;

import desmodevil.javafinal.dto.university.PanEduardUniversityRequestDto;
import desmodevil.javafinal.dto.university.PanEduardUniversityResponseDto;

import java.util.List;

public interface PanEduardUniversityService {

    PanEduardUniversityResponseDto createUniversity(PanEduardUniversityRequestDto requestDto);

    PanEduardUniversityResponseDto getUniversityById(Long id);

    List<PanEduardUniversityResponseDto> getAllUniversities();

    PanEduardUniversityResponseDto updateUniversity(Long id, PanEduardUniversityRequestDto requestDto);

    void deleteUniversity(Long id);
}