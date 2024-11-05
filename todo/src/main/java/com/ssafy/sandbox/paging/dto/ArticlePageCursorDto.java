package com.ssafy.sandbox.paging.dto;

import com.ssafy.sandbox.paging.vo.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArticlePageCursorDto {
    private Integer lastId;
    private List<Article> articles;

    public ArticlePageCursorDto(List<Article> articles) {
        this.articles = articles;
    }
}
