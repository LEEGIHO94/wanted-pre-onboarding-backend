package preonboarding.backend.domain.recruit_announcement.mock;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementPostRequestDto;
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

    public RecruitAnnouncement idMock() {
        return RecruitAnnouncement.builder()
                .id(1L)
                .build();
    }

    public Page<RecruitAnnouncement> pageMock() {
        PageRequest pageRequest = PageRequest.of(0, 30);
        return new PageImpl(listMock(), pageRequest, 11);
    }

    private List<RecruitAnnouncement> listMock() {
        ArrayList<RecruitAnnouncement> list = new ArrayList<>();

        for (Long i = 1L; i <= 10; i++) {
            list.add(mockEntity(i));
        }
        return list;
    }

    private RecruitAnnouncement mockEntity(Long id) {
        return RecruitAnnouncement.builder()
                .id(id)
                .recruitPosition("백엔드 주니어 개발자")
                .compensationForEmployment(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .skill("Python")
                .company(new Company(1L))
                .build();
    }

    public AnnouncementPostRequestDto postDtoMock() {
        return AnnouncementPostRequestDto.builder()
                .companyId(1L)
                .workingArea("서울")
                .skill("Java")
                .recruitPosition("백엔드 개발자")
                .content("원티드에서 백엔드 주니어 개발자를 채용합니다.")
                .compensationForEmployment(200000)
                .build();
    }
}
