package com.bbs.review1109.domain;

import com.bbs.review1109.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleDTO {

    private String title;
    private String contents;

    public Article toEntity(){
        return new Article(title, contents);
    }
}
