package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    @Transactional
    public void deleteByIdv2(Integer id) {
        Board board = findById(id);
        em.remove(board); // PC에 객체를 지우고, (트랜잭션 종료 시) 삭제 쿼리를 전송함
    }

//    @Transactional
//    public void deleteById(Integer id) {
//        Query query = em.createNativeQuery("delete from Board b where b.id = ?");
//        query.setParameter(1, id);
//
//        query.executeUpdate();
//    }

    public Board findById(Integer id) {
        Board board = em.find(Board.class, id); // (class명, PK)
        return board;
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    @Transactional
    public Board save(Board board) {
        // PC에 Data 주소 넣기(Entity만 가능함)
        em.persist(board);
        // 실행후 영속 객체가 됨
        return board;
    }
}