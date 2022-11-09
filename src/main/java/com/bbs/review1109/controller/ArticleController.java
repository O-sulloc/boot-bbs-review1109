package com.bbs.review1109.controller;

import com.bbs.review1109.domain.ArticleDTO;
import com.bbs.review1109.domain.entity.Article;
import com.bbs.review1109.domain.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
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

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "/articles/update";
        } else {
            model.addAttribute("message", String.format("%d은 없습니다.", id));
            return "/articles/error";
        }
    }

    //postMapping 말고 Put으로 (Rest api)
    // pot 요청을 html에서 지원을 안함. PutMapping 못씀
    @PostMapping("/update/{id}")
    public String setUpdate(@PathVariable Long id, ArticleDTO articleDTO, Model model) {
        log.info("title:{} contents:{}", articleDTO.getTitle(), articleDTO.getContents());
        Article article = articleRepository.save(articleDTO.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/getOne/%d", article.getId());
    }


    @GetMapping("/delete/{id}")
    // delete 요청을 html에서 지원을 안함 DeleteMapping 못씀
    // id는 url에서 가져오는 것.
    public String setDelete(@PathVariable Long id) {
        log.info("delete request");

        Article deleteArticleEntity = articleRepository.findById(id).orElse(null);

        if (deleteArticleEntity != null){
            articleRepository.delete(deleteArticleEntity);
        }

        return "redirect:/articles/getList";
    }
}
