package preonboarding.backend.domain.user.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import preonboarding.backend.domain.user.dto.UserPostRequestDto;
import preonboarding.backend.domain.user.entity.User;
import preonboarding.backend.domain.user.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest {

    static final String DEFAULT = "/api/users";
    @Autowired
    MockMvc mvc;
    @MockBean
    UserService service;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("사용자 등록 테스트")
    void post_user_test() throws Exception {
        // given
        User userMock = new User(1L, "김복자", "email@gmail.com", "password!!");
        UserPostRequestDto post = new UserPostRequestDto("email@gmail.com", "김복자", "password!!");

        String content = objectMapper.writeValueAsString(post);

        given(service.postUser(ArgumentMatchers.any(User.class))).willReturn(userMock);
        // when
        ResultActions perform = mvc.perform(
                post(DEFAULT).content(content).contentType(MediaType.APPLICATION_JSON));
        // then
        perform
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userId").value(userMock.getId()));
    }
}