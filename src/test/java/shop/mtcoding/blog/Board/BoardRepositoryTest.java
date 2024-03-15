package shop.mtcoding.blog.Board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test(){
        // given
        int id  = 1;
        String title = "수정제목1";
        String content = "수정내용1";
        // when
        boardRepository.updateById(id, title, content); // delete 쿼리 발동
        em.flush();
        // then
        System.out.println("updateById_test : " + boardRepository.findById(id));
    }

    @Test
    public void deleteById_test(){
        // given
        int id  = 1;
        // when
        boardRepository.deleteById(id); // delete 쿼리 발동
        // then
        System.out.println("deleteById_test : " + boardRepository.findAll().size());
    }

    @Test
    public void findAllV3_test(){
        List<Board> boardList = boardRepository.findAllV2();
        System.out.println("findAllV3_test : 조회완료 쿼리 2번");
        boardList.forEach(board -> {
            System.out.println(board);
        });
    }

    @Test
    public void findAllV2_test(){
        List<Board> boardList = boardRepository.findAllV2();
        System.out.println("findAllV2_test : 조회완료 쿼리 2번");
        boardList.forEach(board -> {
            System.out.println(board);
        });
    }

    @Test
    public void findAll_inqueryv2_test() {
        String q = "SELECT DISTINCT b FROM Board b JOIN FETCH b.user";
        List<Board> boards = em.createQuery(q, Board.class).getResultList();

        for (Board board : boards) {
            System.out.println("Board ID: " + board.getId());
            System.out.println("User ID: " + board.getUser().getId());
            // 사용자(User) 객체에 대한 다른 정보도 필요하다면 추가 가능
        }
    }

    @Test
    public void findAll_inquery_test() {
        // 보드에 해당하는 모든 사용자 ID 조회
        String q1 = "SELECT DISTINCT b.user.id FROM Board b";
        List<Integer> userIds = em.createQuery(q1, Integer.class).getResultList();

        // 각 사용자 ID에 해당하는 보드를 찾아서 사용자 할당
        for (Integer userId : userIds) {
            // 해당 사용자 ID에 해당하는 모든 보드를 찾기
            String q2 = "SELECT b FROM Board b WHERE b.user.id = :userId";
            List<Board> boards = em.createQuery(q2, Board.class)
                    .setParameter("userId", userId)
                    .getResultList();

            // 해당 사용자 ID에 해당하는 사용자를 찾기
            User user = em.find(User.class, userId);

            // 각 보드에 사용자 할당 및 결과 출력
            for (Board board : boards) {
                board.setUser(user);
                System.out.println("Board ID: " + board.getId() + ", User ID: " + user.getId());
            }
        }
    }

    @Test
    public void randomquery_test(){
        int[] ids = {1,2};

        // select u from User u where u.id in (?,?);
        String q = "select u from User u where u.id in (";
        for (int i=0; i<ids.length; i++){
            if(i==ids.length-1){
                q = q + "?)";
            }else{
                q = q + "?,";
            }
        }
        System.out.println(q);
    }

    @Test
    public void findAll_custom_inquery_test() {
        String q = "SELECT DISTINCT b.user.id FROM Board b";
        List<Integer> userIds = em.createQuery(q, Integer.class).getResultList();

        for (Integer userId : userIds) {
            System.out.println(userId);
        }
    }

    @Test
    public void findAll_LazyLoading_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });

        // then
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });

        // then
    }

    @Test
    public void findByIdJoinUser_test() {
        int id = 1;

        boardRepository.findByIdJoinUser(id);
    }

    @Test
    public void findById_test() {
        int id = 1;
        System.out.println("start - 1");
        Board board = boardRepository.findById(id);
        System.out.println("start - 2");
        System.out.println(board.getUser().getId());
        System.out.println("start - 3");
        System.out.println(board.getUser().getUsername());
    }
}