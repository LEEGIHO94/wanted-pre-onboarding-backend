package preonboarding.backend.domain.recruit_announcement.controller;

import static org.springframework.http.HttpStatus.CREATED;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementIdResponseDto;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementPostRequestDto;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
import preonboarding.backend.domain.recruit_announcement.mapper.RecruitAnnouncementMapper;
import preonboarding.backend.domain.recruit_announcement.service.RecruitAnnouncementService;
import preonboarding.backend.dto.ResponseDto;
import preonboarding.backend.utils.UriBuilder;

@RestController
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
public class RecruitAnnouncementController {

    private static final String DEFAULT = "/api/announcement";
    private final RecruitAnnouncementMapper mapper;
    private final RecruitAnnouncementService service;

    @PostMapping
    public ResponseEntity<ResponseDto<AnnouncementIdResponseDto>> postAnnouncement(
            @RequestBody @Valid
            AnnouncementPostRequestDto post) {
        RecruitAnnouncement request = mapper.toEntity(post);

        RecruitAnnouncement result = service.postAnnouncement(request);

        var responseDto = mapper.toResponseDto(result, CREATED);
        URI location = UriBuilder.createUri(DEFAULT, result.getId());

        return ResponseEntity.created(location).body(responseDto);
    }

}
