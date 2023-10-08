package preonboarding.backend.domain.company.mapper;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import preonboarding.backend.domain.company.dto.CompanyIdResponseDto;
import preonboarding.backend.domain.company.dto.CompanyPostRequestDto;
import preonboarding.backend.domain.company.dto.CompanyResponseDto;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
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

    public CompanyResponseDto toResponseDto(Company company, Long announceId) {
        return new CompanyResponseDto(company.getName(), toIdList(company, announceId));
    }

    private CompanyIdResponseDto toResponseIdDto(Company company) {
        return new CompanyIdResponseDto(company.getId());
    }

    private List<Long> toIdList(Company company, Long announceId) {
        return company.getRecruitmentAnnouncementList()
                .stream()
                .map(RecruitAnnouncement::getId)
                .filter(id -> !announceId.equals(id))
                .toList();
    }
}
