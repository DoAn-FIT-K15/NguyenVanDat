package com.dshop.datn.mapper;

import com.dshop.datn.models.Article;
import com.dshop.datn.models.ArticleImage;
import com.dshop.datn.web.dto.request.ArticleRequest;
import com.dshop.datn.web.dto.response.ArticleImageResponse;
import com.dshop.datn.web.dto.response.ArticleResponse;
import org.mapstruct.*;

@Mapper
public interface ArticleMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "categoryId", source = "category.id")
    ArticleResponse mapToResponse(Article article);

    ArticleImageResponse mapImageToResponse(ArticleImage articleImage);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "user.id", source = "userId")
    Article mapRequestToModel(ArticleRequest articleRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget Article article, ArticleRequest articleRequest);
}
