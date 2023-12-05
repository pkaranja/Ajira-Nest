package co.tz.qroo.ajiranest.company;

import co.tz.qroo.ajiranest.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyDTO> findAll() {
        final List<Company> companies = companyRepository.findAll(Sort.by("id"));
        return companies.stream()
                .map(company -> mapToDTO(company, new CompanyDTO()))
                .toList();
    }

    public CompanyDTO get(final UUID id) {
        return companyRepository.findById(id)
                .map(company -> mapToDTO(company, new CompanyDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final CompanyDTO companyDTO) {
        final Company company = new Company();
        mapToEntity(companyDTO, company);
        return companyRepository.save(company).getId();
    }

    public void update(final UUID id, final CompanyDTO companyDTO) {
        final Company company = companyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(companyDTO, company);
        companyRepository.save(company);
    }

    public void delete(final UUID id) {
        companyRepository.deleteById(id);
    }

    private CompanyDTO mapToDTO(final Company company, final CompanyDTO companyDTO) {
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setLogo(company.getLogo());
        companyDTO.setWebsite(company.getWebsite());
        companyDTO.setEmail(company.getEmail());
        companyDTO.setCountry(company.getCountry());
        companyDTO.setSummary(company.getSummary());
        companyDTO.setPhone(company.getPhone());
        companyDTO.setMobile(company.getMobile());
        companyDTO.setActive(company.getActive());
        return companyDTO;
    }

    private Company mapToEntity(final CompanyDTO companyDTO, final Company company) {
        company.setName(companyDTO.getName());
        company.setLogo(companyDTO.getLogo());
        company.setWebsite(companyDTO.getWebsite());
        company.setEmail(companyDTO.getEmail());
        company.setCountry(companyDTO.getCountry());
        company.setSummary(companyDTO.getSummary());
        company.setPhone(companyDTO.getPhone());
        company.setMobile(companyDTO.getMobile());
        company.setActive(companyDTO.getActive());
        return company;
    }

    public boolean nameExists(final String name) {
        return companyRepository.existsByNameIgnoreCase(name);
    }

}
