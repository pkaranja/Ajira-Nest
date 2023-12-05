package co.tz.qroo.ajiranest.skill;

import co.tz.qroo.ajiranest.candidate.Candidate;
import co.tz.qroo.ajiranest.candidate.CandidateRepository;
import co.tz.qroo.ajiranest.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final CandidateRepository candidateRepository;

    public SkillService(final SkillRepository skillRepository,
            final CandidateRepository candidateRepository) {
        this.skillRepository = skillRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<SkillDTO> findAll() {
        final List<Skill> skills = skillRepository.findAll(Sort.by("id"));
        return skills.stream()
                .map(skill -> mapToDTO(skill, new SkillDTO()))
                .toList();
    }

    public SkillDTO get(final UUID id) {
        return skillRepository.findById(id)
                .map(skill -> mapToDTO(skill, new SkillDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final SkillDTO skillDTO) {
        final Skill skill = new Skill();
        mapToEntity(skillDTO, skill);
        return skillRepository.save(skill).getId();
    }

    public void update(final UUID id, final SkillDTO skillDTO) {
        final Skill skill = skillRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(skillDTO, skill);
        skillRepository.save(skill);
    }

    public void delete(final UUID id) {
        skillRepository.deleteById(id);
    }

    private SkillDTO mapToDTO(final Skill skill, final SkillDTO skillDTO) {
        skillDTO.setId(skill.getId());
        skillDTO.setSkill(skill.getSkill());
        skillDTO.setExperience(skill.getExperience());
        skillDTO.setActive(skill.getActive());
        skillDTO.setCandidate(skill.getCandidate() == null ? null : skill.getCandidate().getId());
        return skillDTO;
    }

    private Skill mapToEntity(final SkillDTO skillDTO, final Skill skill) {
        skill.setSkill(skillDTO.getSkill());
        skill.setExperience(skillDTO.getExperience());
        skill.setActive(skillDTO.getActive());
        final Candidate candidate = skillDTO.getCandidate() == null ? null : candidateRepository.findById(skillDTO.getCandidate())
                .orElseThrow(() -> new NotFoundException("candidate not found"));
        skill.setCandidate(candidate);
        return skill;
    }

}
