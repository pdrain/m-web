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


}
