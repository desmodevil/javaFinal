package desmodevil.javafinal.mapper;

import desmodevil.javafinal.dto.instructor.PanEduardInstructorRequestDto;
import desmodevil.javafinal.dto.instructor.PanEduardInstructorResponseDto;
import desmodevil.javafinal.entity.PanEduardDepartment;
import desmodevil.javafinal.entity.PanEduardInstructor;
import org.springframework.stereotype.Component;

@Component
public class PanEduardInstructorMapper {

    public PanEduardInstructor toEntity(
            PanEduardInstructorRequestDto requestDto,
            PanEduardDepartment department
    ) {
        return PanEduardInstructor.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .academicTitle(requestDto.getAcademicTitle())
                .department(department)
                .build();
    }

    public PanEduardInstructorResponseDto toResponseDto(PanEduardInstructor instructor) {
        PanEduardDepartment department = instructor.getDepartment();

        return PanEduardInstructorResponseDto.builder()
                .id(instructor.getId())
                .firstName(instructor.getFirstName())
                .lastName(instructor.getLastName())
                .email(instructor.getEmail())
                .academicTitle(instructor.getAcademicTitle())
                .departmentId(department != null ? department.getId() : null)
                .departmentName(department != null ? department.getName() : null)
                .build();
    }

    public void updateEntity(
            PanEduardInstructor instructor,
            PanEduardInstructorRequestDto requestDto,
            PanEduardDepartment department
    ) {
        instructor.setFirstName(requestDto.getFirstName());
        instructor.setLastName(requestDto.getLastName());
        instructor.setEmail(requestDto.getEmail());
        instructor.setAcademicTitle(requestDto.getAcademicTitle());
        instructor.setDepartment(department);
    }
}