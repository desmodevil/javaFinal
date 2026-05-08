package desmodevil.javafinal.mapper;

import desmodevil.javafinal.dto.department.PanEduardDepartmentRequestDto;
import desmodevil.javafinal.dto.department.PanEduardDepartmentResponseDto;
import desmodevil.javafinal.entity.PanEduardDepartment;
import desmodevil.javafinal.entity.PanEduardUniversity;
import org.springframework.stereotype.Component;

@Component
public class PanEduardDepartmentMapper {

    public PanEduardDepartment toEntity(
            PanEduardDepartmentRequestDto requestDto,
            PanEduardUniversity university
    ) {
        return PanEduardDepartment.builder()
                .name(requestDto.getName())
                .code(requestDto.getCode())
                .university(university)
                .build();
    }

    public PanEduardDepartmentResponseDto toResponseDto(PanEduardDepartment department) {
        PanEduardUniversity university = department.getUniversity();

        return PanEduardDepartmentResponseDto.builder()
                .id(department.getId())
                .name(department.getName())
                .code(department.getCode())
                .universityId(university != null ? university.getId() : null)
                .universityName(university != null ? university.getName() : null)
                .build();
    }

    public void updateEntity(
            PanEduardDepartment department,
            PanEduardDepartmentRequestDto requestDto,
            PanEduardUniversity university
    ) {
        department.setName(requestDto.getName());
        department.setCode(requestDto.getCode());
        department.setUniversity(university);
    }
}