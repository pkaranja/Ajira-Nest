package co.tz.qroo.ajiranest.job;

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
@RequestMapping(value = "/api/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobResource {

    private final JobService jobService;

    public JobResource(final JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(jobService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createJob(@RequestBody @Valid final JobDTO jobDTO) {
        final UUID createdId = jobService.create(jobDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateJob(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final JobDTO jobDTO) {
        jobService.update(id, jobDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteJob(@PathVariable(name = "id") final UUID id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
