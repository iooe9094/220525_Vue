package com.example.dongsungsi.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.dongsungsi.model
 * fileName : Tutorial
 * author : Seok
 * date : 2022-05-25
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-25         Seok          최초 생성
 */

@Getter
@Setter
// 아래가 롬북 라이브러리에서 제공하는 객체 정보를 보여주는 어노테이션
// tutorial@fdsafds -> tutorial 객체의 멤버 변수 값을 모두 보여줌
@ToString
// 용어 : 스네이크 표기법 => 언더바 표기법
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Tutorial {
    private Long id;
    private String title;
    private String description;
    private String published;
    private String deleteYn;
    private String insertTime;
    private String updateTime;
    private String deleteTime;
}