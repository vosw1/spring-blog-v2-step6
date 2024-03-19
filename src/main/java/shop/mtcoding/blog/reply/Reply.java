package shop.mtcoding.blog.reply;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog._core.utils.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor
@Data // 변경되는 데이터에만 setter가 필요함
@Table(name = "reply_tb")
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    // 1(user) 대 N(reply)의 관계
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // user_id

    // 1(board) 대 N(reply)의 관계
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @CreationTimestamp // PC로 인해 DB에 INSERT될 때 날짜 주입
    private Timestamp createdAt;

    @Builder
    public Reply(Integer id, String comment, User user, Board board, Timestamp createdAt) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.board = board;
        this.createdAt = createdAt;
    }
}