package co.tz.qroo.ajiranest.job;

import co.tz.qroo.ajiranest.candidate.Candidate;
import co.tz.qroo.ajiranest.candidate.CandidateRepository;
import co.tz.qroo.ajiranest.company.Company;
import co.tz.qroo.ajiranest.company.CompanyRepository;
import co.tz.qroo.ajiranest.job_category.JobCategory;
import co.tz.qroo.ajiranest.job_category.JobCategoryRepository;
import co.tz.qroo.ajiranest.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class JobService {

    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;
    private final JobCategoryRepository jobCategoryRepository;

    public JobService(final JobRepository jobRepository,
            final CandidateRepository candidateRepository,
            final CompanyRepository companyRepository,
            final JobCategoryRepository jobCategoryRepository) {
        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
        this.jobCategoryRepository = jobCategoryRepository;
    }

    public List<JobDTO> findAll() {
        final List<Job> jobs = jobRepository.findAll(Sort.by("id"));
        return jobs.stream()
                .map(job -> mapToDTO(job, new JobDTO()))
                .toList();
    }

    public JobDTO get(final UUID id) {
        return jobRepository.findById(id)
                .map(job -> mapToDTO(job, new JobDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final JobDTO jobDTO) {
        final Job job = new Job();
        mapToEntity(jobDTO, job);
        return jobRepository.save(job).getId();
    }

    public void update(final UUID id, final JobDTO jobDTO) {
        final Job job = jobRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(jobDTO, job);
        jobRepository.save(job);
    }

    public void delete(final UUID id) {
        jobRepository.deleteById(id);
    }

    private JobDTO mapToDTO(final Job job, final JobDTO jobDTO) {
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setMinExperience(job.getMinExperience());
        jobDTO.setMaxExperience(job.getMaxExperience());
        jobDTO.setAddress(job.getAddress());
        jobDTO.setCity(job.getCity());
        jobDTO.setCountry(job.getCountry());
        jobDTO.setIntro(job.getIntro());
        jobDTO.setResponsibilities(job.getResponsibilities());
        jobDTO.setRequirements(job.getRequirements());
        jobDTO.setApplicationType(job.getApplicationType());
        jobDTO.setWorkLocation(job.getWorkLocation());
        jobDTO.setTags(job.getTags());
        jobDTO.setHits(job.getHits());
        jobDTO.setApplicants(job.getApplicants());
        jobDTO.setActive(job.getActive());
        jobDTO.setEmploymentType(job.getEmploymentType());
        jobDTO.setMaxBudget(job.getMaxBudget());
        jobDTO.setCurrency(job.getCurrency());
        jobDTO.setShowBudget(job.getShowBudget());
        jobDTO.setCandidates(job.getCandidates().stream()
                .map(candidate -> candidate.getId())
                .toList());
        jobDTO.setCompany(job.getCompany() == null ? null : job.getCompany().getId());
        jobDTO.setJobCategory(job.getJobCategory() == null ? null : job.getJobCategory().getId());
        return jobDTO;
    }

    private Job mapToEntity(final JobDTO jobDTO, final Job job) {
        job.setTitle(jobDTO.getTitle());
        job.setMinExperience(jobDTO.getMinExperience());
        job.setMaxExperience(jobDTO.getMaxExperience());
        job.setAddress(jobDTO.getAddress());
        job.setCity(jobDTO.getCity());
        job.setCountry(jobDTO.getCountry());
        job.setIntro(jobDTO.getIntro());
        job.setResponsibilities(jobDTO.getResponsibilities());
        job.setRequirements(jobDTO.getRequirements());
        job.setApplicationType(jobDTO.getApplicationType());
        job.setWorkLocation(jobDTO.getWorkLocation());
        job.setTags(jobDTO.getTags());
        job.setHits(jobDTO.getHits());
        job.setApplicants(jobDTO.getApplicants());
        job.setActive(jobDTO.getActive());
        job.setEmploymentType(jobDTO.getEmploymentType());
        job.setMaxBudget(jobDTO.getMaxBudget());
        job.setCurrency(jobDTO.getCurrency());
        job.setShowBudget(jobDTO.getShowBudget());
        final List<Candidate> candidates = candidateRepository.findAllById(
                jobDTO.getCandidates() == null ? Collections.emptyList() : jobDTO.getCandidates());
        if (candidates.size() != (jobDTO.getCandidates() == null ? 0 : jobDTO.getCandidates().size())) {
            throw new NotFoundException("one of candidates not found");
        }
        job.setCandidates(candidates.stream().collect(Collectors.toSet()));
        final Company company = jobDTO.getCompany() == null ? null : companyRepository.findById(jobDTO.getCompany())
                .orElseThrow(() -> new NotFoundException("company not found"));
        job.setCompany(company);
        final JobCategory jobCategory = jobDTO.getJobCategory() == null ? null : jobCategoryRepository.findById(jobDTO.getJobCategory())
                .orElseThrow(() -> new NotFoundException("jobCategory not found"));
        job.setJobCategory(jobCategory);
        return job;
    }

}
