package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor // 빈생성자 만들어주기
@Data // 변경되는 데이터에만 setter가 필요함
@Table(name = "board_tb")
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;

    @CreationTimestamp // PC로 인해 DB에 INSERT될 때 날짜 주입
    private Timestamp createdAt;

    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    // 다른 곳에서 재사용하려면 DTO이름을 적어둘 수 없음
    public void update(BoardRequest.UpdateDTO reqDTO) { // 변경할 데이터를 설정할 setter 만들기
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        this.username = reqDTO.getUsername();
    }

    public String getBoardDate(){
        return MyDateUtil.timestampFormat(createdAt);
    }
}