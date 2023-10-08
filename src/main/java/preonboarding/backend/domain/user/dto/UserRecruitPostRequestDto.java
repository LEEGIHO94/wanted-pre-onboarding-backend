package preonboarding.backend.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRecruitPostRequestDto {
    @Schema(example = "1",description = "사용자 식별자")
    private Long userId;
    @Setter
    @Schema(hidden = true)
    @Positive
    private Long announcementId;
}
