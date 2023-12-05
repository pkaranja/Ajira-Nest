package co.tz.qroo.ajiranest.candidate;

import co.tz.qroo.ajiranest.job.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "Candidates")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Candidate {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String mobile;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private Boolean currentlyEmployed;

    @Column(unique = true)
    private String linkedInUrl;

    @Column(precision = 10, scale = 2)
    private BigDecimal desiredSalary;

    @Column(length = 10)
    private String currency;

    @Column
    private String resumeFile;

    @Column(columnDefinition = "longtext")
    private String resumeText;

    @Column
    private String coverLetterFile;

    @Column
    private String coverLetterText;

    @Column
    private String githubUrl;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column
    private Boolean active;

    @ManyToMany(mappedBy = "candidates")
    private Set<Job> jobs;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
