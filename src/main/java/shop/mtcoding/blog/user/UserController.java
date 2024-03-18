package shop.mtcoding.blog.user;

import com.sun.source.tree.TryTree;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.update(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser", newSessionUser);
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        userService.join(reqDTO);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
            return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/user/update-form") // session(mypage)에 있으니 id가 필요 없음
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser"); // 절대 nu
        User user = userService.updateForm(sessionUser.getId()); // 없어도 상관은 없음
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}