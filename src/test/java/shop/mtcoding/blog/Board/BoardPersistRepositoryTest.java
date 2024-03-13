package shop.mtcoding.blog.Board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardPersistRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {


    @Autowired //DI
    private BoardPersistRepository boardPersistRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void deleteByIdv2_test(){
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteByIdv2(id);

        em.flush(); // 버퍼에 쥐고 있는 쿼리를 즉시 전송
    }

    @Test
    public void deleteById_test(){
        // given
        int id = 5;

        // when
        Board board = boardPersistRepository.findById(id);
        em.clear(); // PC를 다 비우는 것
        boardPersistRepository.deleteById(id);

        // then
        List<Board> boardList = boardPersistRepository.findAll();
        System.out.println("deleteById_test/size : " + boardList.size());
    }

    @Test
    public void findById_test() {
        //given
        int id = 1;
        //when
        Board board = boardPersistRepository.findById(id);
        boardPersistRepository.findById(id);
        System.out.println("findById_test : " + board);
    }

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