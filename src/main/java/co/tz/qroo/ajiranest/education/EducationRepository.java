package co.tz.qroo.ajiranest.education;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EducationRepository extends JpaRepository<Education, UUID> {
}
