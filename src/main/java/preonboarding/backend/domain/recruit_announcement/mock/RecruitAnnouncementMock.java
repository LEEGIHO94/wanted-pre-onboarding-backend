package preonboarding.backend.domain.recruit_announcement.mock;

import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;

public class RecruitAnnouncementMock {

    public RecruitAnnouncement postAfterMapperMock() {
        return RecruitAnnouncement.builder()
                .recruitPosition("백엔드 주니어 개발자")
                .compensationForEmployment(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .skill("Python")
                .company(new Company(1L))
                .build();
    }

    public RecruitAnnouncement postAfterSaveMock() {
        return RecruitAnnouncement.builder()
                .id(1L)
                .recruitPosition("백엔드 주니어 개발자")
                .compensationForEmployment(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .skill("Python")
                .company(new Company(1L))
                .build();
    }

    public RecruitAnnouncement patchAfterMapperMock() {
        return RecruitAnnouncement.builder()
                .id(1L)
                .recruitPosition("백엔드 시니어 개발자")
                .compensationForEmployment(0)
                .content("원티드랩에서 백엔드 시니어 개발자를 채용합니다.")
                .skill("Java")
                .company(new Company(1L))
                .build();
    }


    public RecruitAnnouncement getAfterRepoMock() {
        return postAfterSaveMock();
    }
}
