package com.ssafy.sandbox.paging.dto;

import com.ssafy.sandbox.paging.vo.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArticlePageOffsetDto {
    private int totalPage;
    private List<Article> articles;
}
