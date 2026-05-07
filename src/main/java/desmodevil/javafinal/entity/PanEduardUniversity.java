package desmodevil.javafinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pan_eduard_universities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanEduardUniversity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 160)
    private String name;

    @Column(nullable = false, unique = true, length = 30)
    private String code;

    @Column(length = 120)
    private String city;

    @Column(length = 120)
    private String country;

    private LocalDate foundedDate;

    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "university")
    @Builder.Default
    private List<PanEduardDepartment> departments = new ArrayList<>();
}