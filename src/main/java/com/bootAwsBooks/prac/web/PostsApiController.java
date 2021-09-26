package com.bootAwsBooks.prac.web;


import com.bootAwsBooks.prac.service.posts.PostsService;
import com.bootAwsBooks.prac.web.dto.PostsResponseDto;
import com.bootAwsBooks.prac.web.dto.PostsSaveRequestDto;
import com.bootAwsBooks.prac.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    /*
    책에는 @PutMapping이라 되어있음 수정해야함
     */
    @PostMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}
