package gdsc.toy_project.domain.album.service;

import gdsc.toy_project.domain.album.dto.response.AlbumListResponse;
import gdsc.toy_project.domain.album.repository.AlbumRepository;
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

    //엘범 리스트 조회하기
    //@Transactional
    //public List<AlbumListResponse> albumListResponseList() {
    //    List<AlbumListResponse> result =
    //            albumRepository.
    //}
}
