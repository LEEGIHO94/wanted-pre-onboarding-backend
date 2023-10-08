package preonboarding.backend.domain.recruit_announcement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementPatchRequestDto {

    @Setter
    @Schema(hidden = true)
    private Long announcementId;
    @Length(max = 32)
    @Schema(description = "근무 지역",example = "부산")
    private String workingArea;
    @Positive
    @Schema(description = "채용 보상금",example = "2000000")
    private Integer compensationForEmployment;
    @Schema(description = "사용 기술",example = "PHP")
    private String skill;
    @Schema(description = "본문",example = "수정된 본문")
    private String content;
    @Schema(description = "채용 포지션",example = "프론트엔드 개발자")
    private String recruitPosition;
}
