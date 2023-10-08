package preonboarding.backend.domain.company.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import preonboarding.backend.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum CompanyExceptionCode implements ExceptionCode {
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "회사가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
