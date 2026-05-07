package desmodevil.javafinal.entity;

import com.paneduard.universitymanagement.enums.PanEduardEnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "pan_eduard_enrollments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_pan_eduard_student_course",
                        columnNames = {"student_id", "course_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanEduardEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private PanEduardStudent student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private PanEduardCourse course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PanEduardEnrollmentStatus status;

    @Column(nullable = false)
    private LocalDateTime enrolledAt;

    @Column(length = 5)
    private String grade;

    @PrePersist
    public void onCreate() {
        this.enrolledAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = PanEduardEnrollmentStatus.ACTIVE;
        }
    }
}