package desmodevil.javafinal.mapper;

import desmodevil.javafinal.dto.student.PanEduardStudentRequestDto;
import desmodevil.javafinal.dto.student.PanEduardStudentResponseDto;
import desmodevil.javafinal.entity.PanEduardDepartment;
import desmodevil.javafinal.entity.PanEduardStudent;
import org.springframework.stereotype.Component;

@Component
public class PanEduardStudentMapper {

    public PanEduardStudent toEntity(
            PanEduardStudentRequestDto requestDto,
            PanEduardDepartment department
    ) {
        return PanEduardStudent.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .dateOfBirth(requestDto.getDateOfBirth())
                .department(department)
                .build();
    }

    public PanEduardStudentResponseDto toResponseDto(PanEduardStudent student) {
        PanEduardDepartment department = student.getDepartment();

        return PanEduardStudentResponseDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .dateOfBirth(student.getDateOfBirth())
                .departmentId(department != null ? department.getId() : null)
                .departmentName(department != null ? department.getName() : null)
                .build();
    }

    public void updateEntity(
            PanEduardStudent student,
            PanEduardStudentRequestDto requestDto,
            PanEduardDepartment department
    ) {
        student.setFirstName(requestDto.getFirstName());
        student.setLastName(requestDto.getLastName());
        student.setEmail(requestDto.getEmail());
        student.setDateOfBirth(requestDto.getDateOfBirth());
        student.setDepartment(department);
    }
}