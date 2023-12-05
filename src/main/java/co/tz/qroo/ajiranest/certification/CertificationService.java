package co.tz.qroo.ajiranest.certification;

import co.tz.qroo.ajiranest.candidate.Candidate;
import co.tz.qroo.ajiranest.candidate.CandidateRepository;
import co.tz.qroo.ajiranest.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final CandidateRepository candidateRepository;

    public CertificationService(final CertificationRepository certificationRepository,
            final CandidateRepository candidateRepository) {
        this.certificationRepository = certificationRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<CertificationDTO> findAll() {
        final List<Certification> certifications = certificationRepository.findAll(Sort.by("id"));
        return certifications.stream()
                .map(certification -> mapToDTO(certification, new CertificationDTO()))
                .toList();
    }

    public CertificationDTO get(final UUID id) {
        return certificationRepository.findById(id)
                .map(certification -> mapToDTO(certification, new CertificationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final CertificationDTO certificationDTO) {
        final Certification certification = new Certification();
        mapToEntity(certificationDTO, certification);
        return certificationRepository.save(certification).getId();
    }

    public void update(final UUID id, final CertificationDTO certificationDTO) {
        final Certification certification = certificationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(certificationDTO, certification);
        certificationRepository.save(certification);
    }

    public void delete(final UUID id) {
        certificationRepository.deleteById(id);
    }

    private CertificationDTO mapToDTO(final Certification certification,
            final CertificationDTO certificationDTO) {
        certificationDTO.setId(certification.getId());
        certificationDTO.setName(certification.getName());
        certificationDTO.setIssuingOrganization(certification.getIssuingOrganization());
        certificationDTO.setIssueDate(certification.getIssueDate());
        certificationDTO.setExpirationDate(certification.getExpirationDate());
        certificationDTO.setExpired(certification.getExpired());
        certificationDTO.setActive(certification.getActive());
        certificationDTO.setCandidate(certification.getCandidate() == null ? null : certification.getCandidate().getId());
        return certificationDTO;
    }

    private Certification mapToEntity(final CertificationDTO certificationDTO,
            final Certification certification) {
        certification.setName(certificationDTO.getName());
        certification.setIssuingOrganization(certificationDTO.getIssuingOrganization());
        certification.setIssueDate(certificationDTO.getIssueDate());
        certification.setExpirationDate(certificationDTO.getExpirationDate());
        certification.setExpired(certificationDTO.getExpired());
        certification.setActive(certificationDTO.getActive());
        final Candidate candidate = certificationDTO.getCandidate() == null ? null : candidateRepository.findById(certificationDTO.getCandidate())
                .orElseThrow(() -> new NotFoundException("candidate not found"));
        certification.setCandidate(candidate);
        return certification;
    }

}
