package gdsc.toy_project.domain.user.service;

import gdsc.toy_project.domain.user.dto.request.SignInDto;
import gdsc.toy_project.domain.user.dto.request.SignUpDto;
import gdsc.toy_project.domain.user.entity.RefreshToken;
import gdsc.toy_project.domain.user.entity.User;
import gdsc.toy_project.domain.user.repository.RefreshTokenRepository;
import gdsc.toy_project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    //아이디 중복 확인
    @Transactional
    public Boolean checkUid(String uid) {
        boolean byUid = userRepository.existsByUid(uid);

        return !byUid;
    }

    //회원가입
    @Transactional
    public Long signUp(SignUpDto user){
        Long id =userRepository.save(
                        User.builder()
                                .uid(user.getUid())
                                .password(passwordEncoder.encode(user.getPassword()))
                                .username(user.getUsername())
                                .build())
                .getId();
        return id;
    }

    //로그인
    @Transactional
    public Boolean signIn (String refreshToken, User user) {
        refreshTokenRepository.save(new RefreshToken(refreshToken));

        log.info(user.getUid() + " (id : " + user.getId() + ") login");
        return true;
    }

    //로그아웃
    @Transactional
    public Boolean signOut(String refreshToken, User user) {
        if (!refreshTokenRepository.existsByRefreshToken(refreshToken))
            return false;

        refreshTokenRepository.deleteByRefreshToken(refreshToken);

        log.info(user.getUid() + " (id : " + user.getId() + ") logout");
        return true;
    }

    //유저 확인
    @Transactional
    public Optional<User> findUserByUid(String uid) {
        Optional<User> member = userRepository.findByUid(uid);
        return member;
    }

    //비밀번호 확인
    @Transactional
    public boolean checkPassword(SignInDto user, User member) {
        return passwordEncoder.matches(user.getPassword(), member.getPassword());
    }
}
