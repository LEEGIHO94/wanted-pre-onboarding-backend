package preonboarding.backend.domain.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.backend.domain.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
