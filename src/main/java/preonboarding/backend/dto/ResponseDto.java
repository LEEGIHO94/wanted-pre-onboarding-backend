package preonboarding.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

    private T data;
    private int code;
    private String message;

    @Builder
    public ResponseDto(T data, HttpStatus status) {
        this.data = data;
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }
}
