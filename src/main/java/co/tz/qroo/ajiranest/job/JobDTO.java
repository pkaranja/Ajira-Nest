package co.tz.qroo.ajiranest.job;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 255)
    private String minExperience;

    private Integer maxExperience;

    @Size(max = 255)
    private String address;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String country;

    @NotNull
    private String intro;

    @NotNull
    private String responsibilities;

    @Size(max = 255)
    private String requirements;

    private ApplicationType applicationType;

    @NotNull
    private WorkLocation workLocation;

    private String tags;

    @NotNull
    private Long hits;

    @NotNull
    private Long applicants;

    @NotNull
    private Boolean active;

    @NotNull
    private EmploymentType employmentType;

    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "19.08")
    private BigDecimal maxBudget;

    @Size(max = 255)
    private String currency;

    @NotNull
    private Boolean showBudget;

    private List<UUID> candidates;

    private UUID company;

    private Long jobCategory;

}
