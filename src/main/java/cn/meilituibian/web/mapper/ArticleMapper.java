package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> articleList(@Param("status") int status);
    int add(Article article);
    Article findById(Long id);
}
