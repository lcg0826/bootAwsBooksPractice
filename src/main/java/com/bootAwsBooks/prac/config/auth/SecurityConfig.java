package com.bootAwsBooks.prac.config.auth;

import com.bootAwsBooks.prac.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정들 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                .authorizeRequests() // URL별 권한 관리 설정 옵션 시작점 , authorizeRequestes 선언되어야 andMatchers 옵션 사용
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // "/" 등 지정된 url들은 permitall() 옵션을 통해 전체 열람 권한 지정
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 권한 관리 대상 옵션 해당 주소를 ㅈ가진 API는 USER 권한을 가진 사람만 가능하도록
                .anyRequest().authenticated() // 설정된 값 외 나머지 url , 여기서는 authenticated를 추가하여 나머지 url들은 모두 인증된 사용자들에게만 허용(로그인한 사용자)
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정 진입점, 로그아웃 성공시 해당 주소로 이동
                .and()
                    .oauth2Login() // OAuth 2 로그인 기능에 대한 여러 설정 진입점
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정ㅇ보를 가져올 때 설정 담당
                        .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 할 userServcie 인터페이스의 구현체를 통해 등록, 리소스서버에 사용자 정보를 가져온 상태에서 추가 진행하려는 기능 명시

    }
}
