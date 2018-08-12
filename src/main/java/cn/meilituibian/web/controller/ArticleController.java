package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Article;
import cn.meilituibian.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("")
    public ModelAndView articleList(@RequestParam(required = false, defaultValue = "-1") int status) {
        List<Article> list = articleService.articleList(status);
        Map<String,Object> models = new HashMap<>();
        models.put("list",list);

        return this.viewResult("articleList",models);
    }

    @RequestMapping("/add-page")
    public ModelAndView page(Article article) {

        return this.viewResult("articleAdd");
    }

    @RequestMapping("/add")
    public String add(Article article) {
        Long id = articleService.add(article);
        return "redirect:/admin/articles";
    }

    @RequestMapping("/update")
    public String update(Article article) {
        articleService.update(article);
        return "redirect:/admin/articles";
    }

    @RequestMapping("/views/{id}")
    public ModelAndView view(@PathVariable Long id) {
        Article article = articleService.findById(id);

        Map<String,Object> models = new HashMap<>();
        models.put("article",article);

        return this.viewResult("articleUpdate",models);
    }
}
