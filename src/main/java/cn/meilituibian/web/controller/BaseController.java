package cn.meilituibian.web.controller;

import cn.meilituibian.web.common.Constants;
import cn.meilituibian.web.domain.MenuInfo;
import cn.meilituibian.web.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController   {

    @Autowired
    private AdminUserService   adminUserService;

    @Autowired
    HttpServletRequest request; //这里可以获取到request



    public  ModelAndView viewResult(String viewName){

        ModelAndView view = new ModelAndView(viewName);

        List<MenuInfo> menus = this.getUserMenu();
        view.addObject("menu", menus);
        return view;
    }



    public  ModelAndView viewResult(String viewName, Map<String,Object> models){

        ModelAndView view = new ModelAndView(viewName);


        for (Map.Entry<String, Object> entry : models.entrySet()) {
            view.addObject(entry.getKey(), entry.getValue());
        }

        List<MenuInfo> menus = this.getUserMenu();
        view.addObject("menu", menus);
        return view;
    }


    private List<MenuInfo> getUserMenu(){
        HttpSession session = request.getSession();
        String user =String.valueOf( session.getAttribute(Constants.USER));
        return this.adminUserService.getMenusByUserName(user);
    }
}
