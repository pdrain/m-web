package cn.meilituibian.web.controller;

import cn.meilituibian.web.common.Constants;
import cn.meilituibian.web.domain.AdminUser;
import cn.meilituibian.web.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController  extends BaseController{
    @RequestMapping("/")
    public ModelAndView index() {
        return this.viewResult("index");

    }

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, String user, String password, String code) {
        ModelAndView view = new ModelAndView();
        HttpSession session = request.getSession();
        Object captchaCode = session.getAttribute(Constants.CAPTCHA_CODE);

        Map<String,Object> models = new HashMap<>();
        String viewName="index";
        //view.setViewName("index");
        if (captchaCode == null || !code.equalsIgnoreCase(captchaCode.toString())) {
            //view.addObject("message", "请输入正确的验证码");
            //viewName="index";
            models.put("message","请输入正确的验证码");
        }
        AdminUser adminUser = adminUserService.getAdminUser(user, password);
        if (adminUser == null) {
            //view.addObject("message", "登录失败");
            //return view;
            models.put("message","登录失败");

        }
        else{
            session.setAttribute(Constants.USER, adminUser);
            viewName="main";
        }

        //view.setViewName("main");
        //return view;


        //models.put("article",article);

        return this.viewResult(viewName,models);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return this.viewResult("index");
    }

    @RequestMapping("/admin/main")
    public ModelAndView main(HttpServletRequest request) {
        return this.viewResult("main");
    }
}
