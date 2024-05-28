package com.caisse.controller;
import com.caisse.dto.ArticleDto;
import com.caisse.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/save")
    public ResponseEntity<ArticleDto> saveArticle(@RequestBody ArticleDto articleDto) {
        ArticleDto savedArticle = articleService.save(articleDto);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

    @GetMapping("article/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable("id") Integer id) {
        ArticleDto articleDto = articleService.findById(id);
        if (articleDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(articleDto, HttpStatus.OK);
    }

    @GetMapping("/byCode/{codeArticle}")
    public ResponseEntity<ArticleDto> getArticleByCode(@PathVariable("codeArticle") String codeArticle) {
        ArticleDto articleDto = articleService.findByCodeArticle(codeArticle);
        if (articleDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(articleDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        List<ArticleDto> articles = articleService.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/byCategory/{idCategory}")
    public ResponseEntity<List<ArticleDto>> getArticlesByCategory(@PathVariable("idCategory") Integer idCategory) {
        List<ArticleDto> articles = articleService.findAllArticleByIdCategory(idCategory);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
