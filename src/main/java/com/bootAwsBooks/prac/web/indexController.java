package com.bootAwsBooks.prac.web;

import com.bootAwsBooks.prac.config.auth.dto.SessionUser;
import com.bootAwsBooks.prac.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    /*
    Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    postsService.findAllDesc() 로 가져온 결과는 posts로 index.mustache에 전달
     */
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("posts", postsService.findAllDesc());

        /*
        CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        로그인 성공시 httpSession.getAttribute("user")에서 값을 가져올 수 있도록
         */
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        /*
        세션에 저장된 값이 있을 때만 model에 userName 저장
         */
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
