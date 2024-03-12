package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/board/save")
    public String save(String title, String content, String username) { // DTO 없이 구현
        boardNativeRepository.save(title, content, username);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) { // Integer : 없으면 null, int : 0
        return "board/detail";
    }
}
