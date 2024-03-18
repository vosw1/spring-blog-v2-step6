package shop.mtcoding.blog.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 자동 컴포넌트 스캔이 됨 -> @Reposirotry를 하지 않아도 메모리에 뜸
public interface UserJPARepository extends JpaRepository<User, Integer> { // 인터페이스는 인터페이스를 상속(extends)할 수 있음

    // 간단한 쿼리 작성하기(join 가능)
    // @Query("select u from User u where u.username = :username and u.password = :password")
    // 쿼리 메서드 -> 복잡도만 올라감, 그냥 쿼리 작성하기
    User findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);
}