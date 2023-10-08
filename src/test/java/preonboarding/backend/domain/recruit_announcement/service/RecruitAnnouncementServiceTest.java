package preonboarding.backend.domain.recruit_announcement.service;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    RecruitAnnouncementMock mock = new RecruitAnnouncementMock();

    @Test
    @DisplayName("채용공고 등록 테스트")
    void post_announcement_test() throws Exception {
        // given
        RecruitAnnouncement announcementMock = mock.postAfterMapperMock();

        RecruitAnnouncement post = mock.postAfterSaveMock();

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
}