package co.tz.qroo.ajiranest.certification;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/certifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificationResource {

    private final CertificationService certificationService;

    public CertificationResource(final CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @GetMapping
    public ResponseEntity<List<CertificationDTO>> getAllCertifications() {
        return ResponseEntity.ok(certificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificationDTO> getCertification(
            @PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(certificationService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createCertification(
            @RequestBody @Valid final CertificationDTO certificationDTO) {
        final UUID createdId = certificationService.create(certificationDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateCertification(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final CertificationDTO certificationDTO) {
        certificationService.update(id, certificationDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCertification(@PathVariable(name = "id") final UUID id) {
        certificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
