package co.tz.qroo.ajiranest.job;

import co.tz.qroo.ajiranest.candidate.Candidate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, UUID> {

    List<Job> findAllByCandidates(Candidate candidate);

}
