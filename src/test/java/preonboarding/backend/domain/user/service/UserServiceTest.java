package preonboarding.backend.domain.user.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import preonboarding.backend.domain.user.entity.User;
import preonboarding.backend.domain.user.exception.UserExceptionCode;
import preonboarding.backend.domain.user.repository.UserRepository;
import preonboarding.backend.exception.BusinessLogicException;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService service;
    @Mock
    UserRepository repository;

    @Test
    @DisplayName("사용자 등록 : 등록 성공")
    void post_user_test() throws Exception {
        // given
        User userMock = new User(1L, "김복자", "email", "password!!");
        User postUser = new User("김복자", "email", "password!!");

        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(any(User.class))).willReturn(userMock);
        // when
        User result = service.postUser(postUser);
        // then
        Assertions.assertThat(result.getId()).isEqualTo(userMock.getId());
    }

    @Test
    @DisplayName("사용자 등록 : 실패 [이미 존재 하는 회원]")
    void post_user_fail_test() throws Exception {
        // given
        User userMock = new User(1L, "김복자", "email", "password!!");
        User postUser = new User("김복자", "email", "password!!");

        given(repository.findByEmail(anyString())).willReturn(Optional.of(userMock));
        given(repository.save(any(User.class))).willReturn(userMock);
        // when
        // then
        Assertions.assertThatThrownBy(() -> service.postUser(postUser)).isInstanceOf(
                BusinessLogicException.class).hasMessage(UserExceptionCode.USER_EXIST.getMessage());
    }
}