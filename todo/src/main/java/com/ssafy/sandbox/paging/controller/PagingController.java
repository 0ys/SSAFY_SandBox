package com.ssafy.sandbox.paging.controller;

import com.ssafy.sandbox.paging.service.PagingService;
import com.ssafy.sandbox.paging.vo.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PagingController {

    private final PagingService pagingService;

    @PostMapping("/make")
    public ResponseEntity<?> postArticles(){
        String url = "https://jsonplaceholder.typicode.com/todos";
        ResponseEntity<Article[]> articles = new RestTemplate().getForEntity(url, Article[].class);
        pagingService.saveArticles(articles);
        return ResponseEntity.ok(articles);
    }

}
