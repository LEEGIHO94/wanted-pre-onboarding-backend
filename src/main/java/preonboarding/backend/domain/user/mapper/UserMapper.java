package preonboarding.backend.domain.user.mapper;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import preonboarding.backend.domain.user.dto.UserIdResponseDto;
import preonboarding.backend.domain.user.dto.UserPostRequestDto;
import preonboarding.backend.domain.user.dto.UserRecruitPostResponseDto;
import preonboarding.backend.domain.user.entity.Recruit;
import preonboarding.backend.domain.user.entity.User;
import preonboarding.backend.domain.user.exception.UserExceptionCode;
import preonboarding.backend.dto.ResponseDto;
import preonboarding.backend.exception.BusinessLogicException;

@Component
public class UserMapper {

    public User toEntity(UserPostRequestDto post) {
        return new User(post.getUsername(), post.getEmail(), post.getPassword());
    }

    public ResponseDto<UserIdResponseDto> toResponseDto(User user, HttpStatus status) {
        return ResponseDto.<UserIdResponseDto>builder()
                .data(toResponseIdDto(user))
                .status(status)
                .build();
    }

    public ResponseDto<UserRecruitPostResponseDto> toRecruitResponseDto(User result,
            Long announceId, HttpStatus httpStatus) {
        return ResponseDto.<UserRecruitPostResponseDto>builder()
                .data(toRecruitResponseIdDto(result, announceId))
                .status(httpStatus)
                .build();
    }

    private UserIdResponseDto toResponseIdDto(User user) {
        return new UserIdResponseDto(user.getId());
    }

    private UserRecruitPostResponseDto toRecruitResponseIdDto(User user, Long announceId) {
        Recruit recruit = byUser(user, announceId);
        return new UserRecruitPostResponseDto(user.getId(),
                recruit.getRecruitAnnouncement().getId(),
                recruit.getRecruitAnnouncement().getCompany().getId(),
                recruit.getRecruitAnnouncement().getCompany().getName());
    }

    private Recruit byUser(User user, Long announceId) {
        List<Recruit> recruitList = user.getRecruitList();
        for (Recruit recruit : recruitList) {
            if (recruit.getRecruitAnnouncement().getId().equals(announceId)) {
                return recruit;
            }
        }
        throw new BusinessLogicException(UserExceptionCode.RECRUIT_NOT_FOUND);
    }

    public User toEntity(Long userId) {
        return new User(userId);
    }


}
