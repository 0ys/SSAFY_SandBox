package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.dto.ArticlePageCursorDto;
import com.ssafy.sandbox.paging.dto.ArticlePageOffsetDto;
import com.ssafy.sandbox.paging.vo.Article;

import java.util.List;

public interface PagingService {

    ArticlePageOffsetDto getArticlesByOffset(int page, int size);
    ArticlePageCursorDto getArticleByCursor(int size, int cursorId);
//    void saveArticles(ResponseEntity<Article[]> articles);
    void saveArticles(List<Article> articles);

}
