package preonboarding.backend.domain.recruit_announcement.dto;

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
    private Long announcementId;
    @Length(max = 32)
    private String workingArea;
    @Positive
    private Integer compensationForEmployment;
    private String skill;
    private String content;
    private String recruitPosition;
}
