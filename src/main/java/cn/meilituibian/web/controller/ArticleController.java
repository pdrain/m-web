package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Article;
import cn.meilituibian.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("")
    public ModelAndView articleList(@RequestParam(required = false, defaultValue = "-1") int status) {
        List<Article> list = articleService.articleList(status);
        ModelAndView view = new ModelAndView("articleList");
        view.addObject("list", list);
        return view;
    }

    @RequestMapping("/add-page")
    public ModelAndView page(Article article) {
        ModelAndView view = new ModelAndView("articleAdd");
        return view;
    }

    @RequestMapping("/add")
    public String add(Article article) {
        Long id = articleService.add(article);
        return "redirect:/admin/articles";
    }

    @RequestMapping("/update")
    public String update(Article article) {
        return "redirect:/admin/articles";
    }

    @RequestMapping("/views/{id}")
    public ModelAndView view(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("articleUpdate");
        Article article = articleService.findById(id);
        view.addObject("article", article);
        return view;
    }
}
