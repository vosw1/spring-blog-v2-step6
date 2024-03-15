package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public User updateById(int id, String password, String email) { // DTO를 안만들고 꺼내서 넘겨도 됨 -> 재사용 가능
        User user = findById(id);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    } // 더티체킹

    public User findById(int id) { // 연관된 객체가 없음 -> 연관된 객체가 있으면 테스트 필요
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO){
        Query query =
                em.createQuery("select u from User u where u.username = :username and u.password =:password", User.class);
        query.setParameter("username", reqDTO.getUsername());
        query.setParameter("password", reqDTO.getPassword());
        return (User) query.getSingleResult();
    }
}