package co.tz.qroo.ajiranest.job_category;

import co.tz.qroo.ajiranest.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    public JobCategoryService(final JobCategoryRepository jobCategoryRepository) {
        this.jobCategoryRepository = jobCategoryRepository;
    }

    public List<JobCategoryDTO> findAll() {
        final List<JobCategory> jobCategories = jobCategoryRepository.findAll(Sort.by("id"));
        return jobCategories.stream()
                .map(jobCategory -> mapToDTO(jobCategory, new JobCategoryDTO()))
                .toList();
    }

    public JobCategoryDTO get(final Long id) {
        return jobCategoryRepository.findById(id)
                .map(jobCategory -> mapToDTO(jobCategory, new JobCategoryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final JobCategoryDTO jobCategoryDTO) {
        final JobCategory jobCategory = new JobCategory();
        mapToEntity(jobCategoryDTO, jobCategory);
        return jobCategoryRepository.save(jobCategory).getId();
    }

    public void update(final Long id, final JobCategoryDTO jobCategoryDTO) {
        final JobCategory jobCategory = jobCategoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(jobCategoryDTO, jobCategory);
        jobCategoryRepository.save(jobCategory);
    }

    public void delete(final Long id) {
        jobCategoryRepository.deleteById(id);
    }

    private JobCategoryDTO mapToDTO(final JobCategory jobCategory,
            final JobCategoryDTO jobCategoryDTO) {
        jobCategoryDTO.setId(jobCategory.getId());
        jobCategoryDTO.setName(jobCategory.getName());
        jobCategoryDTO.setActive(jobCategory.getActive());
        return jobCategoryDTO;
    }

    private JobCategory mapToEntity(final JobCategoryDTO jobCategoryDTO,
            final JobCategory jobCategory) {
        jobCategory.setName(jobCategoryDTO.getName());
        jobCategory.setActive(jobCategoryDTO.getActive());
        return jobCategory;
    }

}
