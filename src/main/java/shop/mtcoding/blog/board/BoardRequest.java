package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String username;

        // Entity로 바꾸는 메서드 - 비영속 객체를 PC에 넣기 위해 insert 때만 필요함
        public Board toEntity(){
            return new Board(title, content, username);
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String username;
    }
}