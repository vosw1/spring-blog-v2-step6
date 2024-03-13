package shop.mtcoding.blog.Board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardPersistRepository;

import java.util.List;


@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {


    @Autowired //DI
    private BoardPersistRepository boardPersistRepository;

    @Test
    public void findAll_test() {
        //given - 지금은 넣을게 없음

        //when
        List<Board> boardList = boardPersistRepository.findAll();

        //then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        //org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void save_test() {
        //given
        Board board = new Board("제목5","내용5","ssar");

        //when
        boardPersistRepository.save(board);
        //then
        System.out.println(board);
    }
}