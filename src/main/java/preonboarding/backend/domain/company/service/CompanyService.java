package preonboarding.backend.domain.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.company.repository.CompanyRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository repository;

    public Company postCompany(Company company) {
        return repository.save(company);
    }

}
