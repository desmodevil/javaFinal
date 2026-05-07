package desmodevil.javafinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pan_eduard_students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanEduardStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String firstName;

    @Column(nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private PanEduardUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private PanEduardDepartment department;

    @OneToMany(mappedBy = "student")
    @Builder.Default
    private List<PanEduardEnrollment> enrollments = new ArrayList<>();
}
