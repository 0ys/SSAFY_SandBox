package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.dto.ArticlePageCursorDto;
import com.ssafy.sandbox.paging.dto.ArticlePageOffsetDto;
import com.ssafy.sandbox.paging.repository.PagingRepository;
import com.ssafy.sandbox.paging.vo.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PagingServiceImpl implements PagingService {

    private final PagingRepository pagingRepository;

    @Override
    public ArticlePageOffsetDto getArticlesByOffset(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Article> articlePage = pagingRepository.findAll(pageRequest);

        List<Article> articles = articlePage.getContent();
        int totalPages = articlePage.getTotalPages();

        return new ArticlePageOffsetDto(totalPages, articles);
    }

    @Override
    public ArticlePageCursorDto getArticleByCursor(int size, int cursorId) {
        PageRequest pageRequest = PageRequest.of(0, size, Sort.by("id").ascending());
        Page<Article> articlePage;
        if(cursorId == 0) {
            articlePage = pagingRepository.findAll(pageRequest);
        } else {
            articlePage = pagingRepository.findByIdGreaterThan(cursorId, pageRequest);
        }

        List<Article> articles = articlePage.getContent();
        if(!articles.isEmpty()) {
            //log.debug("articles: {}", articles);
            int lastId = articles.get(articles.size() - 1).getId();
            return new ArticlePageCursorDto(lastId, articles);
        }
        return new ArticlePageCursorDto(articles);
    }

//    @Transactional
//    @Override
//    public void saveArticles(ResponseEntity<Article[]> articles) {
//        List<Article> articleList = new ArrayList<>();
//        for (Article article : Objects.requireNonNull(articles.getBody())) {
//            articleList.add(Article.builder().id(article.getId()).userId(article.getUserId()).title(article.getTitle()).completed(article.getCompleted()).build());
//        }
//        pagingRepository.saveAll(articleList);
//
//    }

    @Transactional
    @Override
    public void saveArticles(List<Article> articles) {
        pagingRepository.saveAll(articles);
    }
}
