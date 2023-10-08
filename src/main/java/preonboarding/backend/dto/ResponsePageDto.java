package preonboarding.backend.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePageDto<T> {

    PageInfoDto pageInfo;
    private List<T> data;
    private int code;
    private String message;

    @Builder
    public ResponsePageDto(List<T> data, HttpStatus status, int size, int pageNumber, boolean first,
            boolean last, int totalPage) {
        this.data = data;
        this.code = status.value();
        this.pageInfo = new PageInfoDto(size, pageNumber, first, last, totalPage);
        this.message = status.getReasonPhrase();
    }
}
