package preonboarding.backend.domain.user.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;

@Entity
@Getter
@NoArgsConstructor
public class Recruit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "RECRUIT_ID", updatable = false)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private RecruitAnnouncement recruitAnnouncement;
}
