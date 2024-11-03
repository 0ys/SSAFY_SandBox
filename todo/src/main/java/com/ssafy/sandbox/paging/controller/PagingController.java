package com.ssafy.sandbox.paging.controller;

import com.ssafy.sandbox.paging.dto.ArticlePageDto;
import com.ssafy.sandbox.paging.service.PagingService;
import com.ssafy.sandbox.paging.vo.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PagingController {

    private final PagingService pagingService;

    @GetMapping("/paging/offset")
    public ResponseEntity<?> getArticlesByOffset(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        ArticlePageDto articlePage = pagingService.getArticlesByOffset(page-1, size);
        return ResponseEntity.ok(articlePage);
    }

//    @PostMapping("/make")
//    public ResponseEntity<?> postArticles(){
//        String url = "https://jsonplaceholder.typicode.com/todos";
//        ResponseEntity<Article[]> articles = new RestTemplate().getForEntity(url, Article[].class);
//        pagingService.saveArticles(articles);
//        return ResponseEntity.ok(articles);
//    }

    @PostMapping("/make")
    public ResponseEntity<String> makeArticle(@RequestBody Map<String, List<Article>> request) {
        List<Article> articles = request.get("articles");
        pagingService.saveArticles(articles);
        return ResponseEntity.ok("Success");
    }

}
