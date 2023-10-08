package preonboarding.backend.domain.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import preonboarding.backend.domain.user.entity.User;
import preonboarding.backend.domain.user.exception.UserExceptionCode;
import preonboarding.backend.domain.user.repository.UserRepository;
import preonboarding.backend.exception.BusinessLogicException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User postUser(User user) {
        validUserExist(user);

        return repository.save(user);
    }

    private void validUserExist(User user) {
        Optional<User> findUser = repository.findByEmail(user.getEmail());

        if (findUser.isPresent()) {
            throw new BusinessLogicException(UserExceptionCode.USER_EXIST);
        }
    }
}
