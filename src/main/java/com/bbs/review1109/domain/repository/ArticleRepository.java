package com.bbs.review1109.domain.repository;

import com.bbs.review1109.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    //<,id타입>
}
