package desmodevil.javafinal.dto.university;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PanEduardUniversityResponseDto {

    private Long id;

    private String name;

    private String code;

    private String city;

    private String country;

    private LocalDate foundedDate;

    private String description;
}