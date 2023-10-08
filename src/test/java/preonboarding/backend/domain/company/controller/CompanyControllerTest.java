package preonboarding.backend.domain.company.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import preonboarding.backend.domain.company.dto.CompanyPostRequestDto;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.company.service.CompanyService;

@SpringBootTest
@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
class CompanyControllerTest {

    static final String DEFAULT = "/api/companies";
    @Autowired
    MockMvc mvc;
    @MockBean
    CompanyService service;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회사 등록 테스트")
    void post_company_test() throws Exception {
        // given
        Company companyMock = new Company(1L, "원티드");
        CompanyPostRequestDto post = new CompanyPostRequestDto("원티드");

        String content = objectMapper.writeValueAsString(post);

        given(service.postCompany(any(Company.class))).willReturn(companyMock);
        // when
        ResultActions perform = mvc.perform(
                post(DEFAULT).content(content).contentType(MediaType.APPLICATION_JSON));
        // then
        perform
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.companyId")
                        .value(companyMock.getId()));
    }
}