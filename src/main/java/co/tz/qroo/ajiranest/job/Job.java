package co.tz.qroo.ajiranest.job;

import co.tz.qroo.ajiranest.candidate.Candidate;
import co.tz.qroo.ajiranest.company.Company;
import co.tz.qroo.ajiranest.job_category.JobCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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
@Table(name = "Jobs")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Job {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String minExperience;

    @Column
    private Integer maxExperience;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column(nullable = false, columnDefinition = "longtext")
    private String intro;

    @Column(nullable = false, columnDefinition = "longtext")
    private String responsibilities;

    @Column
    private String requirements;

    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationType applicationType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkLocation workLocation;

    @Column(columnDefinition = "longtext")
    private String tags;

    @Column(nullable = false)
    private Long hits;

    @Column(nullable = false)
    private Long applicants;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Column(precision = 10, scale = 2)
    private BigDecimal maxBudget;

    @Column
    private String currency;

    @Column(nullable = false)
    private Boolean showBudget;

    @ManyToMany
    @JoinTable(
            name = "Applicationses",
            joinColumns = @JoinColumn(name = "jobId"),
            inverseJoinColumns = @JoinColumn(name = "candidateId")
    )
    private Set<Candidate> candidates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
