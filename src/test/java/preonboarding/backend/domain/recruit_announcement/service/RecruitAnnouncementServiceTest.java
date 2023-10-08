package preonboarding.backend.domain.recruit_announcement.service;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.company.service.CompanyService;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
import preonboarding.backend.domain.recruit_announcement.exception.RecruitAnnouncementExceptionCode;
import preonboarding.backend.domain.recruit_announcement.mock.RecruitAnnouncementMock;
import preonboarding.backend.domain.recruit_announcement.repository.RecruitAnnouncementRepository;
import preonboarding.backend.exception.BusinessLogicException;

@ExtendWith(MockitoExtension.class)
class RecruitAnnouncementServiceTest {

    @InjectMocks
    RecruitAnnouncementService service;
    @Mock
    RecruitAnnouncementRepository repository;
    @Mock
    CompanyService companyService;
    RecruitAnnouncementMock mock = new RecruitAnnouncementMock();

    @Test
    @DisplayName("채용공고 등록 테스트")
    void post_announcement_test() throws Exception {
        // given
        RecruitAnnouncement announcementMock = mock.postAfterMapperMock();
        Company savedCompany = new Company(1L, "원티드");

        RecruitAnnouncement post = mock.postAfterSaveMock();

        given(companyService.findCompany(any(Company.class))).willReturn(savedCompany);
        given(repository.save(any(RecruitAnnouncement.class)))
                .willReturn(announcementMock);
        // when
        RecruitAnnouncement result = service.postAnnouncement(post);
        // then
        assertThat(result.getId()).isEqualTo(announcementMock.getId());
        assertThat(result).usingRecursiveComparison().isEqualTo(announcementMock);
    }

    @Test
    @DisplayName("채용 공고 수정 : 실패[채용 공고 존재 하지 않음]")
    void patch_announcement_fail_test() throws Exception {
        // given
        RecruitAnnouncement patch = mock.patchAfterMapperMock();

        given(repository.findById(anyLong())).willReturn(Optional.empty());
        // when then
        Assertions.assertThatThrownBy(() -> service.patchAnnouncement(patch))
                .isInstanceOf(BusinessLogicException.class).hasMessage(
                        RecruitAnnouncementExceptionCode.ANNOUNCEMENT_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("채용 공고 수정 : 성공")
    void patch_announcement_test() throws Exception {
        // given
        RecruitAnnouncement patch = mock.patchAfterMapperMock();
        RecruitAnnouncement getResult = mock.getAfterRepoMock();

        given(repository.findById(anyLong())).willReturn(Optional.of(getResult));
        // when
        RecruitAnnouncement result = service.patchAnnouncement(patch);
        //then
        Assertions.assertThat(result.getSkill()).isEqualTo(patch.getSkill());
        Assertions.assertThat(result.getRecruitPosition()).isEqualTo(patch.getRecruitPosition());
        Assertions.assertThat(result.getWorkingArea()).isEqualTo(patch.getWorkingArea());
        Assertions.assertThat(result.getSkill()).isEqualTo(patch.getSkill());
    }

    @Test
    @DisplayName("채용 공고 삭제 : 성공")
    void delete_announcement_test() throws Exception {
        // given
        RecruitAnnouncement delete = mock.idMock();

        willDoNothing().given(repository).deleteById(anyLong());
        // when
        service.deleteAnnouncement(delete);
        // then
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("채용 공고 조회 : 성공")
    void get_announcement_test() throws Exception {
        // given
        RecruitAnnouncement get = mock.getAfterRepoMock();
        RecruitAnnouncement idEntity = mock.idMock();

        given(repository.findById(anyLong())).willReturn(Optional.of(get));
        // when
        RecruitAnnouncement result = service.findAnnouncement(idEntity);
        // then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(get);
    }

    @Test
    @DisplayName("채용 공고 조회 : 성공")
    void get_announcement_page_test() throws Exception {
        // given
        Page<RecruitAnnouncement> pageMock = mock.pageMock();

        given(repository.findPageSearchByParameterOrAll(any(Pageable.class),
                anyString())).willReturn(pageMock);
        // when
        Page<RecruitAnnouncement> result = service.findAnnouncementPage(PageRequest.of(0, 30),
                "Java");
        // then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(pageMock);
    }

}