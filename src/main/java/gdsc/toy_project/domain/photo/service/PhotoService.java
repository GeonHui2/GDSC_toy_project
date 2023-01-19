package gdsc.toy_project.domain.photo.service;

import gdsc.toy_project.domain.photo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhotoService {

    private final PhotoRepository photoRepository;

    // 사진 업로드
    @Transactional
    public
    // 사진 상세 정보 조회

    // 사진 카테고리 변경

    // 사진 삭제
}
