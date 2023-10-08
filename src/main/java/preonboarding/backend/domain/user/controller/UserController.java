package preonboarding.backend.domain.user.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import preonboarding.backend.domain.user.dto.UserIdResponseDto;
import preonboarding.backend.domain.user.dto.UserPostRequestDto;
import preonboarding.backend.domain.user.entity.User;
import preonboarding.backend.domain.user.mapper.UserMapper;
import preonboarding.backend.domain.user.service.UserService;
import preonboarding.backend.dto.ResponseDto;
import preonboarding.backend.utils.UriBuilder;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private static final String DEFAULT = "/api/users";
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<UserIdResponseDto>> postUser(
            @RequestBody UserPostRequestDto post) {
        User request = mapper.toEntity(post);

        User result = service.postUser(request);

        ResponseDto<UserIdResponseDto> responseDto = mapper.toResponseDto(result, CREATED);
        URI location = UriBuilder.createUri(DEFAULT, result.getId());

        return ResponseEntity.created(location).body(responseDto);
    }
}
