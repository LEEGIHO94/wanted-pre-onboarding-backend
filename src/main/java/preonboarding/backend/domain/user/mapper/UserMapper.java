package preonboarding.backend.domain.user.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import preonboarding.backend.domain.user.dto.UserIdResponseDto;
import preonboarding.backend.domain.user.dto.UserPostRequestDto;
import preonboarding.backend.domain.user.entity.User;
import preonboarding.backend.dto.ResponseDto;

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

    private UserIdResponseDto toResponseIdDto(User user) {
        return new UserIdResponseDto(user.getId());
    }
}
