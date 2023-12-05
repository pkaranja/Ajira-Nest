package co.tz.qroo.ajiranest.job_category;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping(value = "/api/jobCategories", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobCategoryResource {

    private final JobCategoryService jobCategoryService;

    public JobCategoryResource(final JobCategoryService jobCategoryService) {
        this.jobCategoryService = jobCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<JobCategoryDTO>> getAllJobCategories() {
        return ResponseEntity.ok(jobCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryDTO> getJobCategory(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(jobCategoryService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createJobCategory(
            @RequestBody @Valid final JobCategoryDTO jobCategoryDTO) {
        final Long createdId = jobCategoryService.create(jobCategoryDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateJobCategory(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final JobCategoryDTO jobCategoryDTO) {
        jobCategoryService.update(id, jobCategoryDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteJobCategory(@PathVariable(name = "id") final Long id) {
        jobCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
