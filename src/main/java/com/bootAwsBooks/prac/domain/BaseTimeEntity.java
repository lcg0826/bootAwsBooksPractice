package com.bootAwsBooks.prac.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing 기능을 포함
public abstract class BaseTimeEntity {

    /*
    java 8 버전 부터 LocalDate와 LocalDateTime 적용
    이전에 사용되었던 Date와 Calandar 클래스는 다음과 같은 문제점이 존재
    1. 불변 객체가 아니다.
    -> 멀티스레드 환경에서 언제든 문제가 발생할 수 있다.

    2. Calendar는 월 (Month) 값 설계가 잘못되었다.
    -> 10월을 나타내는 Calendar.OCTOBER의 숫자 값은 '9'
    -> 당연히 '10'이라고 생각하는 사람들에게는 혼란을 줄 수 있다.
     */

    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장
    private LocalDateTime createDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedDate;
}
