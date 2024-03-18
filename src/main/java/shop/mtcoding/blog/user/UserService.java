package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IoC에 등록
public class UserService { // 컨트롤러는 서비스가, 서비스는 레파지토리가 필요함 - 의존 관계

    private final UserJPARepository userJPARepository;

    public User update (int id, UserRequest.UpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());

       // userJPARepository.save(user);
        return user;
    } // 더티체킹

    public User updateForm (int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return user;
    }


    @Transactional // JPA 레파지토리가 아니라 호출하는 서비스가 가지고 있어야 함
    public User login(UserRequest.LoginDTO reqDTO) {
        // 1. 유효성 검사(컨트롤러 책임)
        // 2. 유저네임 중복검사(서비스 체크) - DB 연결이 필요함
        // 3. hash 값 비교

        // 조회 -> null이면 throw를 날리고 아니면 값을 받음
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증이 되지 않았습니다"));
        return sessionUser;
    }

    @Transactional // JPA 레파지토리가 아니라 호출하는 서비스가 가지고 있어야 함
    public void join(UserRequest.JoinDTO reqDTO) {
        // 1. 유효성 검사(컨트롤러 책임)
        // 2. 유저네임 중복검사(서비스 체크) - DB 연결이 필요함

        // 기존의 유저네임을 조회
        Optional<User> userOp = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOp.isPresent()) {
            throw new Exception400("중복된 유저네임입니다");
        }
        userJPARepository.save(reqDTO.toEntity());
    }
}