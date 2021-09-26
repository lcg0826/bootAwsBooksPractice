package com.bootAwsBooks.prac.web.dto;

import com.bootAwsBooks.prac.domain.posts.Posts;

public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    /*
    Entity 필드 중 일부 사용 -> 생성자로 Entity 받아서 필드에 값 주입
     */
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
