package co.tz.qroo.ajiranest.education;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EducationDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String school;

    @Size(max = 255)
    private String degree;

    @Size(max = 255)
    private String field;

    @NotNull
    private LocalDate start;

    private LocalDate end;

    @NotNull
    private Boolean currentlyPursuing;

    private UUID candidate;

}
