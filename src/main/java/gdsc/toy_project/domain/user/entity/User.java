package gdsc.toy_project.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String uid;
    private String password;
    private String username;

    @Builder
    public User(Long id, String uid, String password, String username) {
        this.id = id;
        this.uid = uid;
        this.password = password;
        this.username = username;
    }

    public void CreateUser(String uid, String password, String username) {
        this.uid = uid;
        this.password = password;
        this.username = username;
    }

}
