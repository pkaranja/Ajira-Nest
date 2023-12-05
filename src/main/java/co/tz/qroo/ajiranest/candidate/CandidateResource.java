package co.tz.qroo.ajiranest.candidate;

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
@RequestMapping(value = "/api/candidates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CandidateResource {

    private final CandidateService candidateService;

    public CandidateResource(final CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getCandidate(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(candidateService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createCandidate(
            @RequestBody @Valid final CandidateDTO candidateDTO) {
        final UUID createdId = candidateService.create(candidateDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateCandidate(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final CandidateDTO candidateDTO) {
        candidateService.update(id, candidateDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCandidate(@PathVariable(name = "id") final UUID id) {
        candidateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
