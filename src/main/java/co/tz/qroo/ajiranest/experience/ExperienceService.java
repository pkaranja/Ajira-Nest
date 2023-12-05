package co.tz.qroo.ajiranest.experience;

import co.tz.qroo.ajiranest.candidate.Candidate;
import co.tz.qroo.ajiranest.candidate.CandidateRepository;
import co.tz.qroo.ajiranest.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final CandidateRepository candidateRepository;

    public ExperienceService(final ExperienceRepository experienceRepository,
            final CandidateRepository candidateRepository) {
        this.experienceRepository = experienceRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<ExperienceDTO> findAll() {
        final List<Experience> experiences = experienceRepository.findAll(Sort.by("id"));
        return experiences.stream()
                .map(experience -> mapToDTO(experience, new ExperienceDTO()))
                .toList();
    }

    public ExperienceDTO get(final UUID id) {
        return experienceRepository.findById(id)
                .map(experience -> mapToDTO(experience, new ExperienceDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final ExperienceDTO experienceDTO) {
        final Experience experience = new Experience();
        mapToEntity(experienceDTO, experience);
        return experienceRepository.save(experience).getId();
    }

    public void update(final UUID id, final ExperienceDTO experienceDTO) {
        final Experience experience = experienceRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(experienceDTO, experience);
        experienceRepository.save(experience);
    }

    public void delete(final UUID id) {
        experienceRepository.deleteById(id);
    }

    private ExperienceDTO mapToDTO(final Experience experience, final ExperienceDTO experienceDTO) {
        experienceDTO.setId(experience.getId());
        experienceDTO.setTitle(experience.getTitle());
        experienceDTO.setCompany(experience.getCompany());
        experienceDTO.setSummary(experience.getSummary());
        experienceDTO.setStartDate(experience.getStartDate());
        experienceDTO.setEndDate(experience.getEndDate());
        experienceDTO.setCurrent(experience.getCurrent());
        experienceDTO.setActive(experience.getActive());
        experienceDTO.setCandidate(experience.getCandidate() == null ? null : experience.getCandidate().getId());
        return experienceDTO;
    }

    private Experience mapToEntity(final ExperienceDTO experienceDTO, final Experience experience) {
        experience.setTitle(experienceDTO.getTitle());
        experience.setCompany(experienceDTO.getCompany());
        experience.setSummary(experienceDTO.getSummary());
        experience.setStartDate(experienceDTO.getStartDate());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setCurrent(experienceDTO.getCurrent());
        experience.setActive(experienceDTO.getActive());
        final Candidate candidate = experienceDTO.getCandidate() == null ? null : candidateRepository.findById(experienceDTO.getCandidate())
                .orElseThrow(() -> new NotFoundException("candidate not found"));
        experience.setCandidate(candidate);
        return experience;
    }

}
