package preonboarding.backend.domain.recruit_announcement.service;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
import preonboarding.backend.domain.recruit_announcement.mock.RecruitAnnouncementMock;
import preonboarding.backend.domain.recruit_announcement.repository.RecruitAnnouncementRepository;

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

        BDDMockito.given(repository.save(any(RecruitAnnouncement.class)))
                .willReturn(announcementMock);
        // when
        RecruitAnnouncement result = service.postAnnouncement(post);
        // then
        assertThat(result.getId()).isEqualTo(announcementMock.getId());
        assertThat(result).usingRecursiveComparison().isEqualTo(announcementMock);
    }

}