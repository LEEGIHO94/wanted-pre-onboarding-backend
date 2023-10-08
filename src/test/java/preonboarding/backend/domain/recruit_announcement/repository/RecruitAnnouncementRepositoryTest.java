package preonboarding.backend.domain.recruit_announcement.repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.company.repository.CompanyRepository;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RecruitAnnouncementRepositoryTest {

    @Autowired
    RecruitAnnouncementRepository recruitAnnouncementRepository;
    @Autowired
    CompanyRepository companyRepository;
    @BeforeEach
    void init() {
        Company one = companyRepository.save(new Company("원티드"));

        List<String> skillList = List.of("Java", "Python", "PHP");
        List<String> recruitPosition = List.of("백엔드 개발자", "프론트엔드 개발자");
        List<String> workingAreaList = List.of("서울", "부산", "대구", "대전", "영월");

        for (Long i = 1L; i <= 10; i++) {
            RecruitAnnouncement announcement = RecruitAnnouncement.builder()
                    .skill(randomDate(skillList))
                    .content("이것은 컨텐츠다.")
                    .recruitPosition(randomDate(recruitPosition))
                    .company(one)
                    .compensationForEmployment(10000)
                    .workingArea(randomDate(workingAreaList))
                    .build();
            recruitAnnouncementRepository.save(announcement);
        }
    }

    @Test
    @DisplayName("search 없을 때 전체 조회")
    void no_search_page_test() throws Exception {

        PageRequest pageRequest = PageRequest.of(0, 30);

        Page<RecruitAnnouncement> result = recruitAnnouncementRepository.findPageSearchByParameterOrAll(
                pageRequest, null);

        Assertions.assertThat(result.getContent()).hasSize(10);

        Page<RecruitAnnouncement> pageSearchByParameterOrAll1 = recruitAnnouncementRepository.findPageSearchByParameterOrAll(
                pageRequest, "Java");

        System.out.println("Vv");
    }


    @Test
    @DisplayName("Java 로 조회")
    void search_java_page_test() throws Exception {

        PageRequest pageRequest = PageRequest.of(0, 30);

        Page<RecruitAnnouncement> result = recruitAnnouncementRepository.findPageSearchByParameterOrAll(
                pageRequest, "Java");

        for (int i = 0; i < result.getContent().size(); i++) {
            Assertions.assertThat(result.getContent().get(i).getSkill()).isEqualTo("Java");
        }
    }

    @Test
    @DisplayName("Java 로 조회")
    void get_test() throws Exception {
        Company one = companyRepository.save(new Company("원티드"));

        RecruitAnnouncement announcement = RecruitAnnouncement.builder()
                .skill("Java")
                .content("이것은 컨텐츠다.")
                .recruitPosition("백엔드")
                .company(one)
                .compensationForEmployment(10000)
                .workingArea("서울")
                .build();
        RecruitAnnouncement save = recruitAnnouncementRepository.save(announcement);

        Optional<RecruitAnnouncement> result = recruitAnnouncementRepository.findById(save.getId());

        Assertions.assertThat(result.get().getSkill()).isEqualTo(announcement.getSkill());
        Assertions.assertThat(result.get().getContent()).isEqualTo(announcement.getContent());
        Assertions.assertThat(result.get().getRecruitPosition())
                .isEqualTo(announcement.getRecruitPosition());
        Assertions.assertThat(result.get().getCompensationForEmployment())
                .isEqualTo(announcement.getCompensationForEmployment());

    }

    @Test
    @DisplayName("Java 로 조회")
    void save_test() throws Exception {
        Company one = companyRepository.save(new Company("원티드"));

        RecruitAnnouncement announcement = RecruitAnnouncement.builder()
                .skill("Java")
                .content("이것은 컨텐츠다.")
                .recruitPosition("백엔드")
                .company(one)
                .compensationForEmployment(10000)
                .workingArea("서울")
                .build();
        RecruitAnnouncement result = recruitAnnouncementRepository.save(announcement);

        Assertions.assertThat(result.getId()).isNotNull();
    }


    private String randomDate(List<String> contentList) {
        Random random = new Random();
        int randomIndex = random.nextInt(contentList.size());
        return contentList.get(randomIndex);

    }
}