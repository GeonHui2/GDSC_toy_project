package gdsc.toy_project.domain.photo.entity;

import gdsc.toy_project.domain.album.entity.Album;
import gdsc.toy_project.global.entity.BaseTimeEntity;
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
}
