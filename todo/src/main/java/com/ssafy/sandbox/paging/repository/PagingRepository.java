package com.ssafy.sandbox.paging.repository;

import com.ssafy.sandbox.paging.vo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagingRepository extends JpaRepository<Article, Integer> {
    Page<Article> findByIdGreaterThan(int cursorId, PageRequest pageRequest);
}
