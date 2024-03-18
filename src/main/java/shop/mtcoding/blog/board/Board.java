package shop.mtcoding.blog.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog._core.utils.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor
@Data // 변경되는 데이터에만 setter가 필요함
@Table(name = "board_tb")
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    // private String username;

    // username은 user_tb에도 있기에 외래키로 연관관계 맺어야 함
    // @JoinColumn(name = "user_id") 직접 지정할 때

    @ManyToOne(fetch = FetchType.LAZY) // ORM 할 것이기에 user 객체 필요
    private User user; // DB에 컬럼명 : user_id (변수명_PK키)

    @CreationTimestamp // PC로 인해 DB에 INSERT될 때 날짜 주입
    private Timestamp createdAt;

    @Transient // 테이블 생성이 안됨
    private boolean isOwner;

    public void update(){

    }

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    public String getBoardDate(){
        return MyDateUtil.timestampFormat(createdAt);
    }
}