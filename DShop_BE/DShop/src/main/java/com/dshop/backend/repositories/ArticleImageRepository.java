package com.dshop.backend.repositories;


import com.dshop.backend.models.Article;
import com.dshop.backend.models.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
	List<ArticleImage> findAllByArticle(Article article);
}

