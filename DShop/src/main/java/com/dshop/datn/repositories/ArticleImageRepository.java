package com.dshop.datn.repositories;

import com.dshop.datn.models.Article;
import com.dshop.datn.models.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
	List<ArticleImage> findAllByArticle(Article article);
}

