package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Category;
import cn.meilituibian.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/category")
public class CategoryController  extends BaseController{
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        List<Category> list = categoryService.categoryList();
        Map<String,Object> models = new HashMap<>();
        models.put("categories",list);

        return this.viewResult("categoryList",models);
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ModelAndView page() {
        List<Category> list = categoryService.categoryList();

        Map<String,Object> models = new HashMap<>();
        models.put("categories",list);

        return this.viewResult("categoryPage",models);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add(Category category) {

        Map<String,Object> models = new HashMap<>();
        try {
            categoryService.save(category);
            List<Category> list = categoryService.categoryList();

            models.put("categories",list);
            models.put("message","增加成功");
        } catch (Exception e) {
            models.put("message", "增加失败");
        }

        return this.viewResult("categoryPage",models);
    }


}
