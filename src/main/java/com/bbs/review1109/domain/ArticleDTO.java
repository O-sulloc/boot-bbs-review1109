package com.bbs.review1109.domain;

import com.bbs.review1109.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleDTO {

    private Long id; //update 위해 id 추가, toEntity에도 추가
    private String title;
    private String contents;

    public Article toEntity() {
        return new Article(this.id, this.title, this.contents);
    }
}
