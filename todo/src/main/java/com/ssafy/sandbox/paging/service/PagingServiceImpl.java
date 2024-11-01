package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.repository.PagingRepository;
import com.ssafy.sandbox.paging.vo.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PagingServiceImpl implements PagingService {

    private final PagingRepository pagingRepository;

    @Transactional
    @Override
    public void saveArticles(ResponseEntity<Article[]> articles) {
        List<Article> articleList = new ArrayList<>();
        for (Article article : Objects.requireNonNull(articles.getBody())) {
            articleList.add(Article.builder().id(article.getId()).userId(article.getUserId()).title(article.getTitle()).completed(article.getCompleted()).build());
        }
        pagingRepository.saveAll(articleList);

    }
}
