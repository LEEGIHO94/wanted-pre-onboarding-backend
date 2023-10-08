package preonboarding.backend.domain.recruit_announcement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;

public interface RecruitAnnouncementRepository extends JpaRepository<RecruitAnnouncement, Long> {

}
