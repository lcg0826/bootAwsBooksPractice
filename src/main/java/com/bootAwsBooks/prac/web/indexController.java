package com.bootAwsBooks.prac.web;

import com.bootAwsBooks.prac.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostsService postsService;

    /*
    Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    postsService.findAllDesc() 로 가져온 결과는 posts로 index.mustache에 전달
     */
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
