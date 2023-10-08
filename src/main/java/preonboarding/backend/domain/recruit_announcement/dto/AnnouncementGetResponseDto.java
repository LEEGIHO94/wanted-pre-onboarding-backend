package preonboarding.backend.domain.recruit_announcement.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.backend.domain.company.dto.CompanyResponseDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementGetResponseDto {

    private Long announcementId;
    private String workingArea;
    private String recruitPosition;
    private Integer compensationForEmployment;
    private String skill;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private CompanyResponseDto companyResponseDto;

}
