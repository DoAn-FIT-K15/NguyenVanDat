package com.dshop.datn.services;

import com.dshop.datn.web.dto.request.ArticleRequest;
import com.dshop.datn.web.dto.response.ArticleResponse;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ArticleService {
    ArticleResponse getArticle(long articleId);

    ArticleResponse getArticleAdmin(long articleId);

    ArticleResponse getArticleByName(String title);

    List<ArticleResponse> getRelatedArticles(long categoryId);

    Pair<List<ArticleResponse>, Integer> getArticles(int pageNo, int pageSize, String sortBy);

    List<ArticleResponse> getArticlesHome();

    Pair<List<ArticleResponse>, Integer> getAllArticles(String keyword,Integer status, int pageNo, int pageSize, String sortBy, boolean desc);

    Pair<List<ArticleResponse>, Integer> getArticleByCategory(int pageNo, int pageSize, String sortBy, long categoryId);

    ArticleResponse createArticle(ArticleRequest articleRequest);

    ArticleResponse updateArticle(long id, ArticleRequest articleRequest);

    String hideArticle(long id);

    String showArticle(long id);

    void delete(long id);

    void deleteImage(long articleImageId);

}
