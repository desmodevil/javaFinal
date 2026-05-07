package desmodevil.javafinal.service.impl;

import desmodevil.javafinal.dto.university.PanEduardUniversityRequestDto;
import desmodevil.javafinal.dto.university.PanEduardUniversityResponseDto;
import desmodevil.javafinal.entity.PanEduardUniversity;
import desmodevil.javafinal.exception.PanEduardResourceNotFoundException;
import desmodevil.javafinal.mapper.PanEduardUniversityMapper;
import desmodevil.javafinal.repository.PanEduardUniversityRepository;
import desmodevil.javafinal.service.PanEduardUniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PanEduardUniversityServiceImpl implements PanEduardUniversityService {

    private final PanEduardUniversityRepository universityRepository;
    private final PanEduardUniversityMapper universityMapper;

    @Override
    @Transactional
    public PanEduardUniversityResponseDto createUniversity(PanEduardUniversityRequestDto requestDto) {
        if (universityRepository.existsByName(requestDto.getName())) {
            throw new IllegalArgumentException("University with this name already exists");
        }

        if (universityRepository.existsByCode(requestDto.getCode())) {
            throw new IllegalArgumentException("University with this code already exists");
        }

        PanEduardUniversity university = universityMapper.toEntity(requestDto);
        PanEduardUniversity savedUniversity = universityRepository.save(university);

        return universityMapper.toResponseDto(savedUniversity);
    }

    @Override
    @Transactional(readOnly = true)
    public PanEduardUniversityResponseDto getUniversityById(Long id) {
        PanEduardUniversity university = findUniversityEntityById(id);
        return universityMapper.toResponseDto(university);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PanEduardUniversityResponseDto> getAllUniversities() {
        return universityRepository.findAll()
                .stream()
                .map(universityMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PanEduardUniversityResponseDto updateUniversity(Long id, PanEduardUniversityRequestDto requestDto) {
        PanEduardUniversity university = findUniversityEntityById(id);

        if (!university.getName().equals(requestDto.getName())
                && universityRepository.existsByName(requestDto.getName())) {
            throw new IllegalArgumentException("University with this name already exists");
        }

        if (!university.getCode().equals(requestDto.getCode())
                && universityRepository.existsByCode(requestDto.getCode())) {
            throw new IllegalArgumentException("University with this code already exists");
        }

        universityMapper.updateEntity(university, requestDto);
        PanEduardUniversity updatedUniversity = universityRepository.save(university);

        return universityMapper.toResponseDto(updatedUniversity);
    }

    @Override
    @Transactional
    public void deleteUniversity(Long id) {
        PanEduardUniversity university = findUniversityEntityById(id);
        universityRepository.delete(university);
    }

    private PanEduardUniversity findUniversityEntityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new PanEduardResourceNotFoundException(
                        "University not found with id: " + id
                ));
    }
}