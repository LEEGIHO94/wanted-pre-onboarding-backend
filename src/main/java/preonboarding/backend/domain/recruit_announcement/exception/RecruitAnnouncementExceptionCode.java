package preonboarding.backend.domain.recruit_announcement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import preonboarding.backend.exception.ExceptionCode;

@Getter
@AllArgsConstructor
public enum RecruitAnnouncementExceptionCode implements ExceptionCode {
    ANNOUNCEMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "채용 공고가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
