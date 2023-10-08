package preonboarding.backend.domain.recruit_announcement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
import preonboarding.backend.domain.recruit_announcement.repository.RecruitAnnouncementRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitAnnouncementService {

    private final RecruitAnnouncementRepository repository;

    public RecruitAnnouncement postAnnouncement(RecruitAnnouncement announcement) {
        return repository.save(announcement);
    }
}
