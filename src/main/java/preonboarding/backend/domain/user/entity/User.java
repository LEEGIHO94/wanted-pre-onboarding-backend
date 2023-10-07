package preonboarding.backend.domain.user.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.backend.domain.Auditable;

@Entity
@Getter
@NoArgsConstructor
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID", updatable = false)
    private Long id;
    @Column(length = 32)
    private String username;
    @Column(unique = true, length = 32)
    private String email;
    @Column(length = 32)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
