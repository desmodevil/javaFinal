package desmodevil.javafinal.dto.university;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PanEduardUniversityRequestDto {

    @NotBlank(message = "University name is required")
    @Size(max = 160, message = "University name must be less than 160 characters")
    private String name;

    @NotBlank(message = "University code is required")
    @Size(max = 30, message = "University code must be less than 30 characters")
    private String code;

    @Size(max = 120, message = "City must be less than 120 characters")
    private String city;

    @Size(max = 120, message = "Country must be less than 120 characters")
    private String country;

    @PastOrPresent(message = "Founded date cannot be in the future")
    private LocalDate foundedDate;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;
}