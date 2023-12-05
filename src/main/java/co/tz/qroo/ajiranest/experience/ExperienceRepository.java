package co.tz.qroo.ajiranest.experience;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExperienceRepository extends JpaRepository<Experience, UUID> {
}
