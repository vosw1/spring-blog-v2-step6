package shop.mtcoding.blog.User;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRequest;

@Import(UserRepository.class) // IoC 등록코드
@DataJpaTest // Datasource(connection pool), EntityManager
public class UserRepositoryTest {

    @Autowired // DI
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test(){
        // given
        int id = 1;
        String password="123456";
        String email = "ssar12@naver.com";
        // when
        userRepository.updateById(id, password, email);
        // then
        em.flush();
        System.out.println("updateById_test : " + id);
        System.out.println("updateById_test : " + password);
        System.out.println("updateById_test : " + email);
    }

    @Test
    public void findById_test(){
        // given
        int id = 1;
        // when
        userRepository.findById(id);
        // then
        System.out.println("findById_test : " + id);
    }

    @Test
    public void findByUsername_test(){
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("ssar");
        reqDTO.setPassword("1234");

        // when
        User user = userRepository.findByUsernameAndPassword(reqDTO);

        // then
    }
}