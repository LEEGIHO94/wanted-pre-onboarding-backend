package preonboarding.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRecruitPostRequestDto {

    private Long userId;
    @Setter
    private Long announcementId;
}
