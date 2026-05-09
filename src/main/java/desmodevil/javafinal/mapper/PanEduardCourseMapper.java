package desmodevil.javafinal.mapper;

import desmodevil.javafinal.dto.course.PanEduardCourseRequestDto;
import desmodevil.javafinal.dto.course.PanEduardCourseResponseDto;
import desmodevil.javafinal.entity.PanEduardCourse;
import desmodevil.javafinal.entity.PanEduardDepartment;
import desmodevil.javafinal.entity.PanEduardInstructor;
import org.springframework.stereotype.Component;

@Component
public class PanEduardCourseMapper {

    public PanEduardCourse toEntity(
            PanEduardCourseRequestDto requestDto,
            PanEduardDepartment department,
            PanEduardInstructor instructor
    ) {
        return PanEduardCourse.builder()
                .title(requestDto.getTitle())
                .code(requestDto.getCode())
                .credits(requestDto.getCredits())
                .description(requestDto.getDescription())
                .department(department)
                .instructor(instructor)
                .build();
    }

    public PanEduardCourseResponseDto toResponseDto(PanEduardCourse course) {
        PanEduardDepartment department = course.getDepartment();
        PanEduardInstructor instructor = course.getInstructor();

        String instructorFullName = null;

        if (instructor != null) {
            instructorFullName = instructor.getFirstName() + " " + instructor.getLastName();
        }

        return PanEduardCourseResponseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .code(course.getCode())
                .credits(course.getCredits())
                .description(course.getDescription())
                .departmentId(department != null ? department.getId() : null)
                .departmentName(department != null ? department.getName() : null)
                .instructorId(instructor != null ? instructor.getId() : null)
                .instructorFullName(instructorFullName)
                .build();
    }

    public void updateEntity(
            PanEduardCourse course,
            PanEduardCourseRequestDto requestDto,
            PanEduardDepartment department,
            PanEduardInstructor instructor
    ) {
        course.setTitle(requestDto.getTitle());
        course.setCode(requestDto.getCode());
        course.setCredits(requestDto.getCredits());
        course.setDescription(requestDto.getDescription());
        course.setDepartment(department);
        course.setInstructor(instructor);
    }
}