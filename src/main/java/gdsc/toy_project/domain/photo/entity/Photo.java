package gdsc.toy_project.domain.photo.entity;

import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.user.entity.User;
import gdsc.toy_project.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Photo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    private String filePath;

    @Enumerated(STRING)
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Photo(String filePath, User user, Category category) {
        this.filePath = filePath;
        this.user = user;
        this.category = category;
    }

    public static Photo addPhoto(String filePath, Category category, User user) {
        return builder()
                .filePath(filePath)
                .category(category)
                .user(user)
                .build();
    }

    public void changeCategory(Category category) {
        this.category = category;
    }
}
