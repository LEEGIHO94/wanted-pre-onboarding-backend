package preonboarding.backend.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@NoArgsConstructor
public class UserPostRequestDto {

    @Email(message = "이메일을 입력하시오")
    private String email;
    @NotNull(message = "필수 값입니다.")
    @Length(max = 20, message = "이름은 최대 20글자 허용")
    private String username;
    @NotNull(message = "필수 값입니다.")
    @Length(max = 32, min = 8, message = "password는 최대 32글자, 최소 8글자 이상 이어야 합니다.")
    private String password;

    public UserPostRequestDto(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
