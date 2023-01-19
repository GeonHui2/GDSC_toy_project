package gdsc.toy_project.domain.album.service;

import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.album.dto.response.AlbumListResponse;
import gdsc.toy_project.domain.album.entity.Album;
import gdsc.toy_project.domain.album.repository.AlbumRepository;
import gdsc.toy_project.domain.user.entity.User;
import gdsc.toy_project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    // 앨범 리스트 조회하기
    public List<AlbumListResponse> albumListResponseList(String uid, Category category) {
        User findUser = userRepository.findByUid(uid).get();
        List<Album> findAlbum = albumRepository.findAllByUserAndCategory(findUser, category);

    }
}
