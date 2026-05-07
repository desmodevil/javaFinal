package desmodevil.javafinal.mapper;

import desmodevil.javafinal.dto.university.PanEduardUniversityRequestDto;
import desmodevil.javafinal.dto.university.PanEduardUniversityResponseDto;
import desmodevil.javafinal.entity.PanEduardUniversity;
import org.springframework.stereotype.Component;

@Component
public class PanEduardUniversityMapper {

    public PanEduardUniversity toEntity(PanEduardUniversityRequestDto requestDto) {
        return PanEduardUniversity.builder()
                .name(requestDto.getName())
                .code(requestDto.getCode())
                .city(requestDto.getCity())
                .country(requestDto.getCountry())
                .foundedDate(requestDto.getFoundedDate())
                .description(requestDto.getDescription())
                .build();
    }

    public PanEduardUniversityResponseDto toResponseDto(PanEduardUniversity university) {
        return PanEduardUniversityResponseDto.builder()
                .id(university.getId())
                .name(university.getName())
                .code(university.getCode())
                .city(university.getCity())
                .country(university.getCountry())
                .foundedDate(university.getFoundedDate())
                .description(university.getDescription())
                .build();
    }

    public void updateEntity(PanEduardUniversity university, PanEduardUniversityRequestDto requestDto) {
        university.setName(requestDto.getName());
        university.setCode(requestDto.getCode());
        university.setCity(requestDto.getCity());
        university.setCountry(requestDto.getCountry());
        university.setFoundedDate(requestDto.getFoundedDate());
        university.setDescription(requestDto.getDescription());
    }
}