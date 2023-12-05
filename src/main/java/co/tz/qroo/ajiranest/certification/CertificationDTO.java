package co.tz.qroo.ajiranest.certification;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CertificationDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String issuingOrganization;

    @NotNull
    @Size(max = 255)
    private String issueDate;

    @Size(max = 255)
    private String expirationDate;

    @NotNull
    private Boolean expired;

    private Boolean active;

    private UUID candidate;

}
