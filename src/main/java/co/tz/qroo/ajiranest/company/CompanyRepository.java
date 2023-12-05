package co.tz.qroo.ajiranest.company;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, UUID> {

    boolean existsByNameIgnoreCase(String name);

}
