package co.tz.qroo.ajiranest.candidate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CandidateDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String middleName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String mobile;

    @NotNull
    @Size(max = 255)
    private String emailAddress;

    @NotNull
    @Size(max = 255)
    private String nationality;

    @NotNull
    private Gender gender;

    @Size(max = 255)
    private String address;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String country;

    private Boolean currentlyEmployed;

    @Size(max = 255)
    private String linkedInUrl;

    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "32.08")
    private BigDecimal desiredSalary;

    @Size(max = 10)
    private String currency;

    @Size(max = 255)
    private String resumeFile;

    private String resumeText;

    @Size(max = 255)
    private String coverLetterFile;

    @Size(max = 255)
    private String coverLetterText;

    @Size(max = 255)
    private String githubUrl;

    @NotNull
    private LocalDate dateOfBirth;

    private Boolean active;

}
