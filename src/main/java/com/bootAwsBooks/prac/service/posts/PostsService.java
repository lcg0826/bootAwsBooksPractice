package com.bootAwsBooks.prac.service.posts;

import com.bootAwsBooks.prac.domain.posts.Posts;
import com.bootAwsBooks.prac.domain.posts.PostsRepository;
import com.bootAwsBooks.prac.web.dto.PostsResponseDto;
import com.bootAwsBooks.prac.web.dto.PostsSaveRequestDto;
import com.bootAwsBooks.prac.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
    update 쿼리 부분이 없는 이유
    - JPA의 영속성 컨텍스트 떄문
    - 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경
    - JPA 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 db에서 데이터를 가져오면 영속성 컨텍스트가 유지된 상태 -> 데이터 변경시 트랜잭션이 끝나는 시점에 해당 테이블 변경분을 반영
    - 즉, 엔티티 객체의 값만 변경하면 별도 쿼리 날릴필요가 X : 더티 체킹
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id ));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id ;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 .... id = " + id ));
        return new PostsResponseDto(entity);
    }
}
