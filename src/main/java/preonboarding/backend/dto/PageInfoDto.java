package preonboarding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoDto {

    private int size;
    private int pageNumber;
    private boolean first;
    private boolean last;
    private int totalPage;
}
