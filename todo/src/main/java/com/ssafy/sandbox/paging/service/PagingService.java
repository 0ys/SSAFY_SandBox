package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.dto.ArticlePageDto;
import com.ssafy.sandbox.paging.vo.Article;

import java.util.List;

public interface PagingService {

    ArticlePageDto getArticlesByOffset(int page, int size);
//    void saveArticles(ResponseEntity<Article[]> articles);
    void saveArticles(List<Article> articles);
}
