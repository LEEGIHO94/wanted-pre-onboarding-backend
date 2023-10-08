package preonboarding.backend.domain.company.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDto {

    private String companyName;
    private List<Long> otherRecruitAnnouncementList;
}
