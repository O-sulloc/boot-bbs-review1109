package com.bbs.review1109.controller;

import com.bbs.review1109.domain.ArticleDTO;
import com.bbs.review1109.domain.entity.Article;
import com.bbs.review1109.domain.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String getNewPage() {

        return "articles/new";
    }

    @PostMapping("")
    public String addArticle(ArticleDTO articleDTO) {

        Article savedArticle = articleRepository.save(articleDTO.toEntity());

        return String.format("redirect:/articles/getOne/%d", savedArticle.getId());
    }

    @GetMapping("getOne/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("articles", optArticle.get());

            return "articles/getOne";
        } else {
            return "articles/error";
        }
    }

    @GetMapping("getList")
    public String getAllList(Model model) {
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute(articleList);

        return "articles/getList";
    }

}
