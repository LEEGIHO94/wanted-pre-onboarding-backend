package preonboarding.backend.domain.company.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import preonboarding.backend.domain.company.dto.CompanyIdResponseDto;
import preonboarding.backend.domain.company.dto.CompanyPostRequestDto;
import preonboarding.backend.domain.company.entity.Company;
import preonboarding.backend.domain.company.mapper.CompanyMapper;
import preonboarding.backend.domain.company.service.CompanyService;
import preonboarding.backend.dto.ResponseDto;
import preonboarding.backend.utils.UriBuilder;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private static final String DEFAULT = "/api/companies";
    private final CompanyService service;
    private final CompanyMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<CompanyIdResponseDto>> postCompany(
            @RequestBody CompanyPostRequestDto post) {
        Company request = mapper.toEntity(post);

        Company result = service.postCompany(request);

        var responseDto = mapper.toResponseDto(result, CREATED);
        URI location = UriBuilder.createUri(DEFAULT, result.getId());

        return ResponseEntity.created(location).body(responseDto);
    }
}
