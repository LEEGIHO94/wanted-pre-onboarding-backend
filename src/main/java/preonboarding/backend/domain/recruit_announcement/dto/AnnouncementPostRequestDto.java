package preonboarding.backend.domain.recruit_announcement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementPostRequestDto {

    @NotNull
    @Positive
    @Schema(description = "회사 식별자",example = "1")
    private Long companyId;
    @NotNull
    @Length(max = 32)
    @Schema(description = "근무 지역",example = "서울")
    private String workingArea;
    @NotNull
    @Positive
    @Schema(description = "취업 보상금",example = "100000")
    private Integer compensationForEmployment;
    @NotNull
    @Schema(description = "사용 기술 스택",example = "Java")
    private String skill;
    @NotNull
    @Schema(description = "본문",example = "본문")
    private String content;
    @NotNull
    @Schema(description = "채용 포지션",example = "백엔드 개발자")
    private String recruitPosition;
}
