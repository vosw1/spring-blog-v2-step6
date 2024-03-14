package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAllV2() { // 다른 정보가 추가로 필요하면 조인하면 됨 - outer join일 때 outer는 생략 가능
        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1, Board.class).getResultList();

        // user의 id가 같으면 Board의 user에 넣어주기 -> String의 필터 사용
        String q2 = "select u from User u where u.id in :userIdList";
        List<User> userList = em.createQuery(q2, User.class).getResultList();

        return boardList; // user가 담겨져 있음
    }

    public List<Board> findAll() { // 다른 정보가 추가로 필요하면 조인하면 됨 - outer join일 때 outer는 생략 가능
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findByIdJoinUser(int id) { // on생략 가능, 알아서 pk연결해줌
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findById(int id) {
        //id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);
        return board;
    }
}