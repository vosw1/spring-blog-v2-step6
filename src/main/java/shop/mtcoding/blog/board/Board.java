package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

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
    private Timestamp createdAt;
}
