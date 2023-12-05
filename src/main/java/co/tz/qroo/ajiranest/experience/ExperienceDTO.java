package co.tz.qroo.ajiranest.experience;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExperienceDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 255)
    private String company;

    @NotNull
    private String summary;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private Boolean current;

    @NotNull
    private Boolean active;

    private UUID candidate;

}
