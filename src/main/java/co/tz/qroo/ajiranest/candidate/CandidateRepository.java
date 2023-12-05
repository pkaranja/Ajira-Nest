package co.tz.qroo.ajiranest.candidate;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    boolean existsByMobileIgnoreCase(String mobile);

    boolean existsByLinkedInIgnoreCase(String linkedIn);

}
