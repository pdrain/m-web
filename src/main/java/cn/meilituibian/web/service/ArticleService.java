package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Article;
import cn.meilituibian.web.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> articleList(int status) {
        return articleMapper.articleList(status);
    }

    @Transactional
    public Long add(Article article) {
        article.setCreateDate(new Date());
        articleMapper.add(article);
        return article.getId();
    }

    @Transactional
    public void update(Article article) {
        articleMapper.update(article);
    }

    public Article findById(Long id) {
        return articleMapper.findById(id);
    }
}
