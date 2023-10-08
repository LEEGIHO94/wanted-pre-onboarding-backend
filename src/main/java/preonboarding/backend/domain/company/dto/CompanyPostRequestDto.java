package preonboarding.backend.domain.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPostRequestDto {
    @Schema(description = "회사 이름",example = "원티드랩")
    private String name;
}
