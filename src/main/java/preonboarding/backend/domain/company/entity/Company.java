package preonboarding.backend.domain.company.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Company(String name) {
        this.name = name;
    }
}
