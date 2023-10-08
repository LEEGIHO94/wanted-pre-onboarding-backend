package preonboarding.backend.domain.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import preonboarding.backend.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum UserExceptionCode implements ExceptionCode {
    USER_EXIST(HttpStatus.CONFLICT, "이미 존재하는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
