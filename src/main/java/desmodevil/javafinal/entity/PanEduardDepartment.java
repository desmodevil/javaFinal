package desmodevil.javafinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pan_eduard_departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanEduardDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private PanEduardUniversity university;

    @OneToMany(mappedBy = "department")
    @Builder.Default
    private List<PanEduardStudent> students = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    @Builder.Default
    private List<PanEduardInstructor> instructors = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    @Builder.Default
    private List<PanEduardCourse> courses = new ArrayList<>();
}