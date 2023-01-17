package gdsc.toy_project.domain.user.service;

import gdsc.toy_project.domain.user.dto.request.SignInDto;
import gdsc.toy_project.domain.user.dto.request.SignUpDto;
import gdsc.toy_project.domain.user.entity.User;
import gdsc.toy_project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    //아이디 중복 확인
    @Transactional
    public Boolean checkUid(String uid) {
        boolean byUid = userRepository.existsByUid(uid);

        return !byUid;
    }

    //회원가입
    @Transactional
    public Long singUp(SignUpDto signUpDto) {
        Long id = userRepository.save(
                        User.builder()
                                .uid(signUpDto.getUid())
                                .password(signUpDto.getPassword())
                                .username(signUpDto.getUsername())
                                .build())
                .getId();

        return id;
    }

    //로그인
    @Transactional
    public Boolean signIn(SignInDto signInDto) {


        return true;
    }
}
