package preonboarding.backend.domain.recruit_announcement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;

public interface RecruitAnnouncementRepository extends JpaRepository<RecruitAnnouncement, Long> {

    @Query("SELECT r FROM RecruitAnnouncement r WHERE " +
            "(:search is null OR " +
            "r.company.name LIKE %:search% OR " +
            "r.skill LIKE %:search% OR " +
            "r.content LIKE %:search% OR " +
            "r.recruitPosition LIKE %:search%)")
    Page<RecruitAnnouncement> findPageSearchByParameterOrAll(Pageable pageable,
            @Param("search") String search);
}
