package co.tz.qroo.ajiranest.skill;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SkillDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String skill;

    @NotNull
    private Integer experience;

    @Size(max = 255)
    private String active;

    private UUID candidate;

}
