package preonboarding.backend.domain.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import preonboarding.backend.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum UserExceptionCode implements ExceptionCode {
    USER_EXIST(HttpStatus.CONFLICT, "이미 존재하는 회원입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    ALREADY_RECRUIT(HttpStatus.BAD_REQUEST, "이미 지원했습니다."),
    RECRUIT_NOT_FOUND(HttpStatus.NOT_FOUND, "지원 정보가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
