package co.tz.qroo.ajiranest.education;

import co.tz.qroo.ajiranest.candidate.Candidate;
import co.tz.qroo.ajiranest.candidate.CandidateRepository;
import co.tz.qroo.ajiranest.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final CandidateRepository candidateRepository;

    public EducationService(final EducationRepository educationRepository,
            final CandidateRepository candidateRepository) {
        this.educationRepository = educationRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<EducationDTO> findAll() {
        final List<Education> educations = educationRepository.findAll(Sort.by("id"));
        return educations.stream()
                .map(education -> mapToDTO(education, new EducationDTO()))
                .toList();
    }

    public EducationDTO get(final UUID id) {
        return educationRepository.findById(id)
                .map(education -> mapToDTO(education, new EducationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final EducationDTO educationDTO) {
        final Education education = new Education();
        mapToEntity(educationDTO, education);
        return educationRepository.save(education).getId();
    }

    public void update(final UUID id, final EducationDTO educationDTO) {
        final Education education = educationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(educationDTO, education);
        educationRepository.save(education);
    }

    public void delete(final UUID id) {
        educationRepository.deleteById(id);
    }

    private EducationDTO mapToDTO(final Education education, final EducationDTO educationDTO) {
        educationDTO.setId(education.getId());
        educationDTO.setSchool(education.getSchool());
        educationDTO.setDegree(education.getDegree());
        educationDTO.setField(education.getField());
        educationDTO.setStart(education.getStart());
        educationDTO.setEnd(education.getEnd());
        educationDTO.setCurrentlyPursuing(education.getCurrentlyPursuing());
        educationDTO.setCandidate(education.getCandidate() == null ? null : education.getCandidate().getId());
        return educationDTO;
    }

    private Education mapToEntity(final EducationDTO educationDTO, final Education education) {
        education.setSchool(educationDTO.getSchool());
        education.setDegree(educationDTO.getDegree());
        education.setField(educationDTO.getField());
        education.setStart(educationDTO.getStart());
        education.setEnd(educationDTO.getEnd());
        education.setCurrentlyPursuing(educationDTO.getCurrentlyPursuing());
        final Candidate candidate = educationDTO.getCandidate() == null ? null : candidateRepository.findById(educationDTO.getCandidate())
                .orElseThrow(() -> new NotFoundException("candidate not found"));
        education.setCandidate(candidate);
        return education;
    }

}
