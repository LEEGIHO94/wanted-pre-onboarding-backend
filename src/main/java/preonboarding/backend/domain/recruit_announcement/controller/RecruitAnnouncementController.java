package preonboarding.backend.domain.recruit_announcement.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementGetDetailResponseDto;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementIdResponseDto;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementPatchRequestDto;
import preonboarding.backend.domain.recruit_announcement.dto.AnnouncementPostRequestDto;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;
import preonboarding.backend.domain.recruit_announcement.mapper.RecruitAnnouncementMapper;
import preonboarding.backend.domain.recruit_announcement.service.RecruitAnnouncementService;
import preonboarding.backend.dto.ResponseDto;
import preonboarding.backend.dto.ResponsePageDto;
import preonboarding.backend.utils.UriBuilder;

@RestController
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
@Tag(name = "채용 공고 API", description = "채용 공고 등록, 수정 삭제 등 채용 공고 관련 API")
public class RecruitAnnouncementController {

    private static final String DEFAULT = "/api/announcement";
    private final RecruitAnnouncementMapper mapper;
    private final RecruitAnnouncementService service;

    @PostMapping
    @Operation(summary = "채용 공고 등록", description = "채용 공고 등록")
    public ResponseEntity<ResponseDto<AnnouncementIdResponseDto>> postAnnouncement(
            @RequestBody @Valid
            AnnouncementPostRequestDto post) {
        RecruitAnnouncement request = mapper.toEntity(post);

        RecruitAnnouncement result = service.postAnnouncement(request);

        var responseDto = mapper.toIdResponseDto(result, CREATED);
        URI location = UriBuilder.createUri(DEFAULT, result.getId());

        return ResponseEntity.created(location).body(responseDto);
    }

    @PatchMapping("/{announcement-id}")
    @Operation(description = "채용 공고에 대한 수정 \n 수정이  필요한 필드만 채워서 전송\n 테스트 시 announcementId = 1 로 테스트",summary = "채용 공고에 대한 수정")
    public ResponseEntity<ResponseDto<AnnouncementIdResponseDto>> patchAnnouncement(
            @PathVariable("announcement-id") Long announcementId,
            @RequestBody @Valid
            AnnouncementPatchRequestDto patch) {
        patch.setAnnouncementId(announcementId);
        RecruitAnnouncement request = mapper.toEntity(patch);

        RecruitAnnouncement result = service.patchAnnouncement(request);

        var responseDto = mapper.toIdResponseDto(result, OK);
        URI location = UriBuilder.createUri(DEFAULT, result.getId());

        return ResponseEntity.ok().header("location", location.toString()).body(responseDto);
    }

    @DeleteMapping("/{announcement-id}")
    @Operation(description = "채용 공고 삭제 \n 테스트 시 announcementId = 1로 테스트", summary = "채용 공고 삭제")
    public ResponseEntity deleteAnnouncement(@PathVariable("announcement-id") Long announcementId) {
        RecruitAnnouncement request = mapper.toEntity(announcementId);

        service.deleteAnnouncement(request);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{announcement-id}")
    @Operation(description = "채용 공고 단건 조회 \n 테스트 시 announcementId = 1로 테스트", summary = "채용 공고 단건 조회")
    public ResponseEntity<ResponseDto<AnnouncementGetDetailResponseDto>> findAnnouncement(
            @PathVariable("announcement-id") Long announcementId) {
        RecruitAnnouncement request = mapper.toEntity(announcementId);

        RecruitAnnouncement result = service.findAnnouncement(request);

        var responseDto = mapper.toResponseDto(result, OK);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @Operation(description = "채용 공고 전체 조회 \n search 가 없을 경우 전체 조회 진행 \n url?page=0&size=30&sort=id과 같은 방식으로 요청, 기본값이 각각 존재", summary = "채용 공고 조건별 검색")
    public ResponseEntity<ResponsePageDto<AnnouncementGetDetailResponseDto>> findAnnouncementPage(
            @Parameter(hidden = true)Pageable pageable,
            @RequestParam(name = "search", required = false) String search) {

        Page<RecruitAnnouncement> result = service.findAnnouncementPage(pageable, search);
        var responseDto = mapper.toPageResponseDto(result, OK);

        return ResponseEntity.ok(responseDto);
    }
}
