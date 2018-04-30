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

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
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
        ModelAndView view =  new ModelAndView("projectAdd");
        List<Category> categories = categoryService.getUnFinishCateogryList();
        view.addObject("categories", categories);
        return view;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private ModelAndView list(@RequestParam(value = "categoryId", required = false) Long categoryId, @RequestParam(value = "categoryName", required = false) String categoryName) {
        ModelAndView view =  new ModelAndView("projectList");
        List<Project> list = projectService.projectList(categoryId);
        view.addObject("projects", list);

        List<Category> secondCategoryList = categoryService.getCategoryListByGrade(2L);
        view.addObject("categories", secondCategoryList);
        return view;
    }
}
