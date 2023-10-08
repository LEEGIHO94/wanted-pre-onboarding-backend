package preonboarding.backend.domain.recruit_announcement.dto;

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
    private Long companyId;
    @NotNull
    @Length(max = 32)
    private String workingArea;
    @NotNull
    @Positive
    private Integer compensationForEmployment;
    @NotNull
    private String skill;
    @NotNull
    private String content;
    @NotNull
    private String recruitPosition;
}
