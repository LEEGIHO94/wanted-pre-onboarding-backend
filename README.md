## 프로젝트 소개
  
- 기업의 채용을 위한 웹 서비스 백엔드 API
- 배포 URL : http://3.37.87.77
- 사용한 port : 8080 
## 구조
```sh
  backend
    ┗domain
       ┗ controller
       ┗service
       ┗repository
       ┗exception
       ┗dto
    ┗dto
    ┗excetpion
    ┗utils
    ┗config 
```
- domain : 각 도메인이 구현되어 있습니다. 각각 controller, service, repository, exception, dto 로 구현되어 있습니다.
     - dto : 각 도메인에서 사용하는 dto가 구현 되어 있습니다.
     - exception : 각 도메인에서 발생하는 예외 코드를 구현했습니다.
- dto : 공통으로 사용하는 Wrapping dto가 구현 되어 있습니다.
- exception : 예외 처리를 위한 AOP가 구현되어 있습니다.
- utils : 공통으로 사용하는 utils들이 구현 되어 있습니다.
- config : 설정 관련 파일들이 구현되어 있습니다.

실행 방법
1. git clone 
```sh
  git cloen https://github.com/LEEGIHO94/wanted-pre-onboarding-backend.git
```
2. 프로젝트 파일 이동
```sh
  cd wanted-pre-onboarding-backend
```
3. 프로젝트 실행
```sh
  ./gradlew build -PactiveProfile=prod --exclude-task test
  java -jar -Dspring.profiles.active=prod build/libs/backend-0.0.1-SNAPSHOT.jar
```
- test완료 시 소나큐브에 등록하는 과정에서 소나큐브 url을 등록하지 않을 경우 에러가 발생하기 때문에 이를 방지 하기  위해 테스트 제외한 빌드 진행
- 구현한 테스트의 경우는 사진 파일 첨부

## 배포
![image](https://github.com/LEEGIHO94/wanted-pre-onboarding-backend/assets/116015708/04b91382-00a2-4286-b923-ff7d9e2dbcef)
- EC2를 통해 프로젝트의 배포 진행
- DataBase는 RDS(MySQL)를 활용해서 구현
- RDS, EC2 같은 VPC에 넣어 RDS 접속을 EC2에서만 가능하게 제한해 RDS에 대한 보안 추가

### 배포 순서
![image](https://github.com/LEEGIHO94/wanted-pre-onboarding-backend/assets/116015708/f651ac5b-f29b-4e97-8c90-221f7a313dbd)

1. Build 시 테스트 진행
2. 테스트 이 후 테스트 결과를 바탕으로 테스트 커버리지 수집(jacoco)
3. sonarqube에 데이터 전달
    1. 성공 시 Build
    2. 실패 시 Build Fail

## 핵심기능

- 채용공고 등록
- 채용공고 수정
- 채용공고 삭제
- 채용공고 목록 조회
- 채용공고 키워드 검색
- 채용상세 조회
- 채용공고 지원

## 사용 기술 스택

- Java 17
- SpringBoot 3.1.4
- Spring Data JPA
- JUnit5
- MySQL 8.0.31

## 요구사항 분석 및 구현과정
<img width="851" alt="image" src="https://github.com/LEEGIHO94/wanted-pre-onboarding-backend/assets/116015708/43a30330-a209-4725-932d-c1dd51cb081b">

- 채용 공고 포스트를 만드는 것이 핵심인 서비스로 company와 user에는 간단한 등록 API 를 구현했습니다.
- 필수 기술요건 요구사항에 맞춰 Java/Spring 과 Spring Data JPA, MySQL을 사용했습니다.
- 채용 공고 키워드 검색의 경우 키워드를 입력하지 않았을 때는 전체 데이터가 조회되도록 구현했습니다.
- 채용 공고 키워드 검색의 구현 시 전체 데이터가 입력 되도록 구현 했습니다.
- Recruit의 경우 User 도메인에 포함 시켰습니다.
- 모든 데이터의 이동은 채용공고 키워드 검색 제외 Entity로 이동했습니다.
     - 메서드의 재활용 가능성을 위해 선택했습니다.
     - 계층간의 결합도를 낮추기 위해 구현했습니다.
- 요구사항(핵심기능)을 충족하기 위해 데이터베이스를 아래와 같이 모델링했습니다.

## ERD
<img width="1028" alt="image" src="https://github.com/LEEGIHO94/wanted-pre-onboarding-backend/assets/116015708/deeee022-42ce-4f78-af79-806b088aa229">

- User : 유저 테이블로 추 후 시큐리티 로직 적용을 대비하기 위해 password 까지 구현
- Recruit : N:M 연관관계를 1:N, M:1로 변경해주기 위해 구현한 연결 테이블
- Company : 회사 테이블로 간단하게 이름만 가지고 있게 구현
- Recruit_Announcement : 채용 공고 테이블
URL : [ERD_LINK](https://www.erdcloud.com/d/xkXnsBGJBsmgv9bps)

## API 명세서

- [Swagger API 명세서](http://3.37.87.77:8080/swagger-ui/index.html)
  - post는 예시 데이터를 추가해둔 상태


### Test Code

- **도메인 단위 테스트 (Unit Test)**
    - JUnit5를 사용해서 단위테스트를 작성했습니다.
    - 예외 처리가 필요할 경우 예외 처리 테스트 진행했습니다.
    - 주입 받은 인스턴스의 메서드를 사용하는 경우 단위테스트의 의미가 없기 때문에 테스트를 진행하지 않았습니다.

## :construction_worker: API 구현과정

### 채용공고 등록
- 채용 공고 등록 시 userId를 받아 공고를 등록했습니다.
- RequestBody에 채용공고 등록요청 DTO를 파라미터로 받도록 구현했습니다.
<details>
<summary><strong> postAnnouncement</strong></summary>
<div markdown="1">       

```java
    public ResponseEntity<ResponseDto<AnnouncementIdResponseDto>> postAnnouncement(
            @RequestBody @Valid
            AnnouncementPostRequestDto post) {
        RecruitAnnouncement request = mapper.toEntity(post);

        RecruitAnnouncement result = service.postAnnouncement(request);

        var responseDto = mapper.toIdResponseDto(result, CREATED);
        URI location = UriBuilder.createUri(DEFAULT, result.getId());

        return ResponseEntity.created(location).body(responseDto);
    }
```

</div>
</details>

- companyId 로 company 테이블 DB 조회 & 객체를 가져옵니다.
- dto 와 company(FK)로 recruitment(채용공고) 객체를 생성 및 DB에 저장하여 채용공고를 등록합니다.

---

### 채용공고 수정
- Pathvariable로 announcementId 사용
  - 채용 공고 수정의 경우 수정할 필드에만 데이터를 입력하도록 구현했습니다.
- RequestBody에 채용공고 수정요청 DTO를 파라미터로 받도록 구현했습니다.
<details>
<summary><strong> patchAnnouncement</strong></summary>
<div markdown="1">       

```java
    @PatchMapping("/{announcement-id}")
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
```

</div>
</details>

---

### 채용공고 삭제
- Pathvariable로 announcementId 사용
- 채용공고가 존재하지 않아도 삭제가 되어야하는 멱등성을 유지했습니다.
<details>
<summary><strong> deleteAnnouncement</strong></summary>
<div markdown="1">       

```java
    @DeleteMapping("/{announcement-id}")
    public ResponseEntity deleteAnnouncement(@PathVariable("announcement-id") Long announcementId) {
        RecruitAnnouncement request = mapper.toEntity(announcementId);

        service.deleteAnnouncement(request);

        return ResponseEntity.noContent().build();
    }
```

</div>
</details>

---

### 채용공고 키워드 검색
- RequestParam 으로 search (ex)/api/announcement?search="원티드") 를 사용
- search 에 데이터를 넣지 않으면 전체 데이터가 출력되도록 구현했습니다.
- 구현을 위해 동적 쿼리를 이용해서 구현했습니다.
- 작성한 쿼리에 대한 단위 테스트를 통해 작동 여부 확인
<details>
<summary><strong> findAnnouncementPage</strong></summary>
<div markdown="1">       

```java
    @GetMapping
    public ResponseEntity<ResponsePageDto<AnnouncementGetDetailResponseDto>> findAnnouncementPage(
            Pageable pageable,
            @RequestParam(name = "search", required = false) String search) {

        Page<RecruitAnnouncement> result = service.findAnnouncementPage(pageable, search);
        var responseDto = mapper.toPageResponseDto(result, OK);

        return ResponseEntity.ok(responseDto);
    }
```
</div>
</details>

<details>
<summary><strong> findPageSearchByParameterOrAll - DB에서 동적 쿼리로 조회 하는 메서드</strong></summary>
<div markdown="1">       

```java
    @Query("SELECT r FROM RecruitAnnouncement r WHERE " +
            "(:search is null OR " +
            "r.company.name LIKE %:search% OR " +
            "r.skill LIKE %:search% OR " +
            "r.content LIKE %:search% OR " +
            "r.recruitPosition LIKE %:search%)")
    Page<RecruitAnnouncement> findPageSearchByParameterOrAll(Pageable pageable,
            @Param("search") String search);
    }
```
</div>
</details>

---

### 채용상세 페이지 조회
- Pathvariable로 announcementId 사용
<details>
<summary><strong> findAnnouncement</strong></summary>
<div markdown="1">       

```java
    @GetMapping("/{announcement-id}")
    public ResponseEntity<ResponseDto<AnnouncementGetDetailResponseDto>> findAnnouncement(
            @PathVariable("announcement-id") Long announcementId) {
        RecruitAnnouncement request = mapper.toEntity(announcementId);

        RecruitAnnouncement result = service.findAnnouncement(request);

        var responseDto = mapper.toResponseDto(result, OK);

        return ResponseEntity.ok(responseDto);
    }
```
</div>
</details>
---

### 채용공고 지원
- 채용 공고 지원은 User 도메인에서 구현을 했습니다.
- RequestBody에 채용공고 지원요청 DTO(userId)를 파라미터로 받으며 Pathvariable로 announcementId 받습니다.
- 사용자는 채용 공고 상세페이지에서 지원을 하기 때문에 항상 announcementId를 알고 있으며 userId의 경우는 로그인을 할 경우 파악할 수 있기 때문에 userId, announcementId로 파라미터를 정했습니다.
<details>
<summary><strong> postRecruit</strong></summary>
<div markdown="1">       

```java
   @PostMapping("/recruit/{announcement-id}")
    public ResponseEntity<ResponseDto<UserRecruitPostResponseDto>> postRecruit(
            @PathVariable("announcement-id") Long announceId,
            @RequestBody UserRecruitPostRequestDto post) {
        post.setAnnouncementId(announceId);
        User requestUser = userMapper.toEntity(post.getUserId());
        RecruitAnnouncement requestAnnounce = announcementMapper.toEntity(post.getAnnouncementId());

        User result = service.recruit(requestUser, requestAnnounce);

        ResponseDto<UserRecruitPostResponseDto> responseDto = userMapper.toRecruitResponseDto(
                result, requestAnnounce.getId(), HttpStatus.OK);

        return ResponseEntity.ok(responseDto);
    }
```

</div>
</details>

- 채용 공고 지원은 Recruit Announcement 보다는 User와 더 연관이 있다고 판단 User 도메인에 구현
- 연결 테이블의 역할을 수행하며 RecruitAnnouncement 와 User의 관계인 N:M 을 1:N, M:1의 관계로 변경하기 위해 사용
- Status Enum 클래스를 통해 지원여부 상태를 구분합니다.
- 이미 지원한 상태라면 지원하지 못하도록 예외처리가 적용되어 있습니다.
<details>
<summary><strong> apply - recruitment(domain)</strong></summary>
<div markdown="1">       

```java
public void apply(UserRecruitment userRecruitment) {
        if(existsByUserRecruitment(userRecruitment)) {
            throw new ApiException(CustomErrorCode.ALREADY_EXISTS_APPLYING);
        }
        userRecruitments.add(userRecruitment);
    }

    private boolean existsByUserRecruitment(UserRecruitment userRecruitment) {
        return this.userRecruitments.contains(userRecruitment);
    }
```

</div>
</details>

## 테스트
<img width="338" alt="image" src="https://github.com/LEEGIHO94/wanted-pre-onboarding-backend/assets/116015708/2bb82f39-0c07-40e2-887b-082bcf6552d6">
진행한 테스트 모두 성공

## 소나큐브를 활용한 리펙토링
<img width="988" alt="image" src="https://github.com/LEEGIHO94/wanted-pre-onboarding-backend/assets/116015708/b2525ae9-a578-4cb8-904a-82e0c4abb9c2">
- 정적 분석 도구를 활용해 코드의 품질 향상을 위한 수정 진행


