package com.bootAwsBooks.prac.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
@Target
- 이 어노테이션이 생성될 수 있는 위치 지정
- parameter로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용 가능
- 외에도 클래스 선언문에 쓸 수 있는 type 등 존재
 */
/*
@interface
- 이 파일을 어노테이션 클래스로 지정
- LoginUser 라는 이름을 가진 어노테이션이 생성
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
