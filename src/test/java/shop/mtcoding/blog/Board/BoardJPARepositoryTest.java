package shop.mtcoding.blog.Board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    // save
    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();
        // when
        boardJPARepository.save(board);
        // then
        System.out.println("save_test : " + board.getId());
    }

    // findById
    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        if (boardOP.isPresent()) {
            Board board = boardOP.get();
            System.out.println("findById_test : " + board.getUser());
            System.out.println("findById_test : " + board.getTitle());
            System.out.println("findById_test : " + board.getContent());
        }
        // then

    }

    // findByIdJoinUser
    @Test
    public void findByIdJoinUserAndReplies_test() {
        // given
        int id = 4;
        // when
        Optional<Board> board = boardJPARepository.findByIdJoinUserAndReplies(id);
        // then
        System.out.println("findByIdJoinUser_test : " + board);
    }

    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;
        // when
        Optional<Board> board = boardJPARepository.findByIdJoinUser(id);
        // then
    }

    //findAll
    @Test
    public void findAll_test() {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // when
        List<Board> boardList = boardJPARepository.findAll(sort);
        // then
        System.out.println("findAll_test : " + boardList);
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;
        // when
        boardJPARepository.deleteById(id);
        boardJPARepository.flush(); // 영속성 컨텍스트의 변경사항을 즉시 데이터베이스에 반영
        // then
    }
}
