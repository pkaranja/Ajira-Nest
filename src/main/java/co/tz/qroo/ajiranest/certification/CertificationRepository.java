package co.tz.qroo.ajiranest.certification;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CertificationRepository extends JpaRepository<Certification, UUID> {
}
