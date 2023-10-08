package preonboarding.backend.domain.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.company.exception.CompanyExceptionCode;
import preonboarding.backend.domain.company.repository.CompanyRepository;
import preonboarding.backend.exception.BusinessLogicException;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository repository;

    public Company postCompany(Company company) {
        return repository.save(company);
    }

    public Company findCompany(Company company) {
        return repository.findById(company.getId()).orElseThrow(() -> new BusinessLogicException(
                CompanyExceptionCode.COMPANY_NOT_FOUND));
    }
}
