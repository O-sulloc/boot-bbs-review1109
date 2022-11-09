package com.bbs.review1109.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "article3") //테이블 이륾 지정
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 id 생성을 맡기겠다. primary key로 생성됨.
    private Long id; //int로 커버가 안 될 경우가 있음. long이 나음.

    private String title;
    private String contents;

    public Article(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
