package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Category;
import cn.meilituibian.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        List<Category> list = categoryService.categoryList();
        ModelAndView view = new ModelAndView("categoryList");
        view.addObject("categories", list);
        return view;
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ModelAndView page() {
        List<Category> list = categoryService.categoryList();
        ModelAndView view = new ModelAndView("categoryPage");
        view.addObject("categories", list);
        return view;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add(Category category) {
        ModelAndView view = new ModelAndView("categoryPage");
        try {
            categoryService.save(category);
            List<Category> list = categoryService.categoryList();

            view.addObject("categories", list);
            view.addObject("message", "增加成功");
        } catch (Exception e) {
            view.addObject("message", "增加失败");
        }
        return view;
    }


}
