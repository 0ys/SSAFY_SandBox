package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.vo.Article;
import org.springframework.http.ResponseEntity;

public interface PagingService {
    void saveArticles(ResponseEntity<Article[]> articles);
}
