package preonboarding.backend.domain.user.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
import preonboarding.backend.domain.recruit_announcement.service.RecruitAnnouncementService;
import preonboarding.backend.domain.user.entity.Recruit;
import preonboarding.backend.domain.user.entity.User;
import preonboarding.backend.domain.user.exception.UserExceptionCode;
import preonboarding.backend.domain.user.repository.UserRepository;
import preonboarding.backend.exception.BusinessLogicException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RecruitAnnouncementService recruitAnnouncementService;
    public User postUser(User user) {
        validUserExist(user);

        return userRepository.save(user);
    }

    public User recruit(User user, RecruitAnnouncement recruitAnnouncement) {
        User findUser = validUser(user);
        RecruitAnnouncement findAnnouncement = recruitAnnouncementService.validAnnouncement(
                recruitAnnouncement.getId());

        List<Recruit> recruitList = findUser.getRecruitList();
        for (Recruit recruit : recruitList) {
            if (recruit.getRecruitAnnouncement().getId().equals(recruitAnnouncement.getId())) {
                throw new BusinessLogicException(UserExceptionCode.ALREADY_RECRUIT);
            }
        }

        recruitList.add(new Recruit(findUser, findAnnouncement));
        return findUser;
    }

    private void validUserExist(User user) {
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());

        if (findUser.isPresent()) {
            throw new BusinessLogicException(UserExceptionCode.USER_EXIST);
        }
    }

    private User validUser(User user) {
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new BusinessLogicException(UserExceptionCode.USER_NOT_FOUND));
    }
}
