package shop.mtcoding.blog.board;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, String title, String content, String username){
        boardNativeRepository.updateById(id, title, content, username);
        return "redirect:/board/"+id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable (name="id") Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "/board/update-form"; // 서버가 내부적으로 index를 요청 - 외부에서는 다이렉트 접근이 안됨
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) { // DTO 없이 구현
        boardNativeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        // 조회하기
        List<Board> boardList = boardNativeRepository.findAll();
        // 가방에 담기
        request.setAttribute("boardList", boardList);

        return "index"; // 서버가 내부적으로 index를 요청 - 외부에서는 다이렉트 접근이 안됨
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
    public String detail(@PathVariable Integer id, HttpServletRequest request) { // Integer : 없으면 null, int : 0
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/detail";
    }
}
