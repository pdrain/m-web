package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Category;
import cn.meilituibian.web.domain.Project;
import cn.meilituibian.web.service.CategoryService;
import cn.meilituibian.web.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/project")
public class ProjectController  extends BaseController{
    @Autowired
    private ProjectService projectService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private String addProject(Project project) {
        projectService.addProject(project);
        return "redirect:/project/list";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    private ModelAndView page() {
        List<Category> categories = categoryService.getUnFinishCateogryList();

        Map<String,Object> models = new HashMap<>();
        models.put("categories",categories);

        return this.viewResult("projectAdd",models);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private ModelAndView list(@RequestParam(value = "categoryId", required = false) Long categoryId, @RequestParam(value = "categoryName", required = false) String categoryName) {
        List<Project> list = projectService.projectList(categoryId);

        List<Category> secondCategoryList = categoryService.getCategoryListByGrade(2L);

        Map<String,Object> models = new HashMap<>();
        models.put("projects",list);
        models.put("categories",secondCategoryList);

        return this.viewResult("projectList",models);
    }
}
