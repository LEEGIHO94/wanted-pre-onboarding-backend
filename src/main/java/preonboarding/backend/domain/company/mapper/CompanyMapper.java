package preonboarding.backend.domain.company.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import preonboarding.backend.domain.company.dto.CompanyIdResponseDto;
import preonboarding.backend.domain.company.dto.CompanyPostRequestDto;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.dto.ResponseDto;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyPostRequestDto post) {
        return new Company(post.getName());
    }

    public ResponseDto<CompanyIdResponseDto> toResponseDto(Company company, HttpStatus status) {
        return ResponseDto.<CompanyIdResponseDto>builder()
                .data(toResponseIdDto(company))
                .status(status)
                .build();
    }

    private CompanyIdResponseDto toResponseIdDto(Company company) {
        return new CompanyIdResponseDto(company.getId());
    }
}
