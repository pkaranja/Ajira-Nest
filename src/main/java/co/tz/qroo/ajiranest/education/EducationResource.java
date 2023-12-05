package co.tz.qroo.ajiranest.education;

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
@RequestMapping(value = "/api/educations", produces = MediaType.APPLICATION_JSON_VALUE)
public class EducationResource {

    private final EducationService educationService;

    public EducationResource(final EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public ResponseEntity<List<EducationDTO>> getAllEducations() {
        return ResponseEntity.ok(educationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationDTO> getEducation(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(educationService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createEducation(
            @RequestBody @Valid final EducationDTO educationDTO) {
        final UUID createdId = educationService.create(educationDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateEducation(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final EducationDTO educationDTO) {
        educationService.update(id, educationDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteEducation(@PathVariable(name = "id") final UUID id) {
        educationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
