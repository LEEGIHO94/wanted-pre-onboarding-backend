package preonboarding.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRecruitPostResponseDto {

    private Long userId;
    private Long announcementId;
    private Long companyId;
    private String companyName;
}
