package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.dto.ArticlePageDto;
import com.ssafy.sandbox.paging.repository.PagingRepository;
import com.ssafy.sandbox.paging.vo.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PagingServiceImpl implements PagingService {

    private final PagingRepository pagingRepository;

    @Override
    public ArticlePageDto getArticlesByOffset(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Article> articlePage = pagingRepository.findAll(pageRequest);

        List<Article> articles = articlePage.getContent();
        int totalPages = articlePage.getTotalPages();

        return new ArticlePageDto(totalPages, articles);
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
