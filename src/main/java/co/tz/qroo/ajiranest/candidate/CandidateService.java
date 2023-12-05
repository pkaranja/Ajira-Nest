package co.tz.qroo.ajiranest.candidate;

import co.tz.qroo.ajiranest.job.JobRepository;
import co.tz.qroo.ajiranest.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;

    public CandidateService(final CandidateRepository candidateRepository,
            final JobRepository jobRepository) {
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
    }

    public List<CandidateDTO> findAll() {
        final List<Candidate> candidates = candidateRepository.findAll(Sort.by("id"));
        return candidates.stream()
                .map(candidate -> mapToDTO(candidate, new CandidateDTO()))
                .toList();
    }

    public CandidateDTO get(final UUID id) {
        return candidateRepository.findById(id)
                .map(candidate -> mapToDTO(candidate, new CandidateDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final CandidateDTO candidateDTO) {
        final Candidate candidate = new Candidate();
        mapToEntity(candidateDTO, candidate);
        return candidateRepository.save(candidate).getId();
    }

    public void update(final UUID id, final CandidateDTO candidateDTO) {
        final Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(candidateDTO, candidate);
        candidateRepository.save(candidate);
    }

    public void delete(final UUID id) {
        final Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        jobRepository.findAllByCandidates(candidate)
                .forEach(job -> job.getCandidates().remove(candidate));
        candidateRepository.delete(candidate);
    }

    private CandidateDTO mapToDTO(final Candidate candidate, final CandidateDTO candidateDTO) {
        candidateDTO.setId(candidate.getId());
        candidateDTO.setFirstName(candidate.getFirstName());
        candidateDTO.setMiddleName(candidate.getMiddleName());
        candidateDTO.setLastName(candidate.getLastName());
        candidateDTO.setMobile(candidate.getMobile());
        candidateDTO.setEmailAddress(candidate.getEmailAddress());
        candidateDTO.setNationality(candidate.getNationality());
        candidateDTO.setGender(candidate.getGender());
        candidateDTO.setAddress(candidate.getAddress());
        candidateDTO.setCity(candidate.getCity());
        candidateDTO.setCountry(candidate.getCountry());
        candidateDTO.setCurrentlyEmployed(candidate.getCurrentlyEmployed());
        candidateDTO.setLinkedInUrl(candidate.getLinkedInUrl());
        candidateDTO.setDesiredSalary(candidate.getDesiredSalary());
        candidateDTO.setCurrency(candidate.getCurrency());
        candidateDTO.setResumeFile(candidate.getResumeFile());
        candidateDTO.setResumeText(candidate.getResumeText());
        candidateDTO.setCoverLetterFile(candidate.getCoverLetterFile());
        candidateDTO.setCoverLetterText(candidate.getCoverLetterText());
        candidateDTO.setGithubUrl(candidate.getGithubUrl());
        candidateDTO.setDateOfBirth(candidate.getDateOfBirth());
        candidateDTO.setActive(candidate.getActive());
        return candidateDTO;
    }

    private Candidate mapToEntity(final CandidateDTO candidateDTO, final Candidate candidate) {
        candidate.setFirstName(candidateDTO.getFirstName());
        candidate.setMiddleName(candidateDTO.getMiddleName());
        candidate.setLastName(candidateDTO.getLastName());
        candidate.setMobile(candidateDTO.getMobile());
        candidate.setEmailAddress(candidateDTO.getEmailAddress());
        candidate.setNationality(candidateDTO.getNationality());
        candidate.setGender(candidateDTO.getGender());
        candidate.setAddress(candidateDTO.getAddress());
        candidate.setCity(candidateDTO.getCity());
        candidate.setCountry(candidateDTO.getCountry());
        candidate.setCurrentlyEmployed(candidateDTO.getCurrentlyEmployed());
        candidate.setLinkedInUrl(candidateDTO.getLinkedInUrl());
        candidate.setDesiredSalary(candidateDTO.getDesiredSalary());
        candidate.setCurrency(candidateDTO.getCurrency());
        candidate.setResumeFile(candidateDTO.getResumeFile());
        candidate.setResumeText(candidateDTO.getResumeText());
        candidate.setCoverLetterFile(candidateDTO.getCoverLetterFile());
        candidate.setCoverLetterText(candidateDTO.getCoverLetterText());
        candidate.setGithubUrl(candidateDTO.getGithubUrl());
        candidate.setDateOfBirth(candidateDTO.getDateOfBirth());
        candidate.setActive(candidateDTO.getActive());
        return candidate;
    }

    public boolean mobileExists(final String mobile) {
        return candidateRepository.existsByMobileIgnoreCase(mobile);
    }

    public boolean linkedInUrlExists(final String linkedInUrl) {
        return candidateRepository.existsByLinkedInUrlIgnoreCase(linkedInUrl);
    }

}
