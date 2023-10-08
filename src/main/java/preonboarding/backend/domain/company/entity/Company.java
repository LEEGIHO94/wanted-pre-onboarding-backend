package preonboarding.backend.domain.company.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.backend.domain.recruit_announcement.entity.RecruitAnnouncement;

@Entity
@Getter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "COMPANY_ID", updatable = false)
    private Long id;
    @Column(length = 32)
    private String name;

    @OneToMany
    private List<RecruitAnnouncement> recruitmentAnnouncementList = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
