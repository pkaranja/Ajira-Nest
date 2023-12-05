package co.tz.qroo.ajiranest.company;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CompanyDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String logo;

    @Size(max = 255)
    private String website;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String country;

    private String summary;

    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String mobile;

    @NotNull
    private Boolean active;

}
