package gdsc.toy_project.domain.photo.entity;

import gdsc.toy_project.domain.album.entity.Album;
import gdsc.toy_project.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String name;
    private String file_path;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @Builder
    public Photo(Long id, String name, String file_path, Album album) {
        this.id = id;
        this.name = name;
        this.file_path = file_path;
        this.album = album;
    }

    public void CreatePhoto(String name, String file_path, Album album) {
        this.name = name;
        this.file_path = file_path;
        this.album = album;
    }
}
