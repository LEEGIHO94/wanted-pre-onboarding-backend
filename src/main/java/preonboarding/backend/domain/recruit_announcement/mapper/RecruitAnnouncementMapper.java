package preonboarding.backend.domain.recruit_announcement.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.company.mapper.CompanyMapper;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementGetDetailResponseDto;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementIdResponseDto;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementPatchRequestDto;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementPostRequestDto;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
import preonboarding.backend.dto.ResponseDto;
import preonboarding.backend.dto.ResponsePageDto;

@Component
@RequiredArgsConstructor
public class RecruitAnnouncementMapper {

    private final CompanyMapper mapper;

    public RecruitAnnouncement toEntity(AnnouncementPostRequestDto post) {
        return RecruitAnnouncement.builder()
                .recruitPosition(post.getRecruitPosition())
                .content(post.getContent())
                .skill(post.getSkill())
                .company(new Company(post.getCompanyId()))
                .compensationForEmployment(post.getCompensationForEmployment())
                .workingArea(post.getWorkingArea())
                .build();
    }

    public RecruitAnnouncement toEntity(AnnouncementPatchRequestDto patch) {
        return RecruitAnnouncement.builder()
                .id(patch.getAnnouncementId())
                .recruitPosition(patch.getRecruitPosition())
                .content(patch.getContent())
                .skill(patch.getSkill())
                .compensationForEmployment(patch.getCompensationForEmployment())
                .workingArea(patch.getWorkingArea())
                .build();
    }

    public RecruitAnnouncement toEntity(Long announcementId) {
        return RecruitAnnouncement.builder()
                .id(announcementId)
                .build();
    }

    public ResponseDto<AnnouncementIdResponseDto> toIdResponseDto(RecruitAnnouncement result,
            HttpStatus status) {
        return ResponseDto.<AnnouncementIdResponseDto>builder()
                .data(toIdResponse(result))
                .status(status)
                .build();
    }

    public ResponseDto<AnnouncementGetDetailResponseDto> toResponseDto(RecruitAnnouncement result,
            HttpStatus httpStatus) {
        return ResponseDto.<AnnouncementGetDetailResponseDto>builder()
                .data(toResponse(result))
                .status(httpStatus)
                .build();
    }

    public ResponsePageDto<AnnouncementGetDetailResponseDto> toPageResponseDto(
            Page<RecruitAnnouncement> result, HttpStatus status) {

        return ResponsePageDto.<AnnouncementGetDetailResponseDto>builder()
                .pageNumber(result.getPageable().getPageNumber())
                .totalPage(result.getTotalPages())
                .first(result.isFirst())
                .last(result.isLast())
                .status(status)
                .data(toListResponse(result.getContent()))
                .build();
    }


    private AnnouncementIdResponseDto toIdResponse(RecruitAnnouncement recruitAnnouncement) {
        return new AnnouncementIdResponseDto(recruitAnnouncement.getId());
    }

    private AnnouncementGetDetailResponseDto toResponse(RecruitAnnouncement recruitAnnouncement) {
        return AnnouncementGetDetailResponseDto.builder()
                .announcementId(recruitAnnouncement.getId())
                .createdDate(recruitAnnouncement.getCreatedAt())
                .modifiedDate(recruitAnnouncement.getModifiedAt())
                .compensationForEmployment(recruitAnnouncement.getCompensationForEmployment())
                .recruitPosition(recruitAnnouncement.getRecruitPosition())
                .workingArea(recruitAnnouncement.getWorkingArea())
                .compensationForEmployment(recruitAnnouncement.getCompensationForEmployment())
                .skill(recruitAnnouncement.getSkill())
                .companyResponseDto(mapper.toResponseDto(recruitAnnouncement.getCompany(),
                        recruitAnnouncement.getId()))
                .content(recruitAnnouncement.getContent())
                .build();
    }

    private List<AnnouncementGetDetailResponseDto> toListResponse(List<RecruitAnnouncement> list) {
        return list.stream().map(this::toResponse).toList();
    }
}