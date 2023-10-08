package preonboarding.backend.domain.recruit_announcement.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementPatchRequestDto;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementPostRequestDto;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
import preonboarding.backend.domain.recruit_announcement.mock.RecruitAnnouncementMock;
import preonboarding.backend.domain.recruit_announcement.service.RecruitAnnouncementService;

@SpringBootTest
@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
class RecruitAnnouncementControllerTest {

    private static final String DEFAULT = "/api/announcement";
    @Autowired
    MockMvc mvc;
    @MockBean
    RecruitAnnouncementService service;
    RecruitAnnouncementMock mock = new RecruitAnnouncementMock();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("채용공고 등록")
    void post_announcement_test() throws Exception {
        // given
        AnnouncementPostRequestDto post = mock.postDtoMock();
        String content = objectMapper.writeValueAsString(post);

        RecruitAnnouncement resultMock = mock.postAfterSaveMock();

        given(service.postAnnouncement(ArgumentMatchers.any(RecruitAnnouncement.class)))
                .willReturn(resultMock);
        // when
        ResultActions perform = mvc.perform(MockMvcRequestBuilders.post(DEFAULT).content(content)
                .contentType(MediaType.APPLICATION_JSON));
        // then
        perform
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.announcementId")
                        .value(resultMock.getId()));
    }

    @Test
    @DisplayName("채용 공고 수정")
    void patch_announcement_test() throws Exception {
        // given
        AnnouncementPatchRequestDto patch = mock.patchDtoMock();
        String content = objectMapper.writeValueAsString(patch);

        RecruitAnnouncement resultMock = mock.postAfterSaveMock();

        given(service.patchAnnouncement(ArgumentMatchers.any(RecruitAnnouncement.class)))
                .willReturn(resultMock);
        // when
        ResultActions perform = mvc.perform(
                MockMvcRequestBuilders.patch(DEFAULT + "/{announcement-id}", 1L).content(content)
                        .contentType(MediaType.APPLICATION_JSON));
        // then
        perform
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.announcementId")
                        .value(resultMock.getId()));
    }

    @Test
    @DisplayName("채용 공고 삭제")
    void delete_announcement_test() throws Exception {
        // given

        willDoNothing().given(service)
                .deleteAnnouncement(ArgumentMatchers.any(RecruitAnnouncement.class));

        // when
        ResultActions perform = mvc.perform(
                MockMvcRequestBuilders.delete(DEFAULT + "/{announcement-id}", 1L));
        // then
        perform
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}