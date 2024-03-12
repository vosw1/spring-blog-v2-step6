package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

// 나중에 안쓰고 새로운 레파지토리를 쓰고 이건 버릴거임
@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager em;

    @Transactional
    public void save(String title, String content, String username){
        Query query = em.createNativeQuery("insert into board_tb (title, content, username, created_at) values (?,?,?,now())");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        query.executeUpdate();
    }
}
