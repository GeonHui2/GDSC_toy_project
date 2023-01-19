package gdsc.toy_project.domain.album.entity;

import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.photo.entity.Photo;
import gdsc.toy_project.domain.user.entity.User;
import gdsc.toy_project.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Album extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "album_id")
    private Long id;

    private String name;
    private Long count;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    @Enumerated(STRING)
    private Category category;

    @Builder
    public Album(Long id, String name, Long count, User user, List<Photo> photos, Category category) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.user = user;
        this.photos = photos;
        this.category = category;
    }
}
