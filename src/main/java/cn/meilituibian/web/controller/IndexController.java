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

@Controller
public class IndexController {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, String user, String password, String code) {
        ModelAndView view = new ModelAndView();
        HttpSession session = request.getSession();
        Object captchaCode = session.getAttribute(Constants.CAPTCHA_CODE);
        view.setViewName("index");
        if (captchaCode == null || !code.equalsIgnoreCase(captchaCode.toString())) {
            view.addObject("message", "请输入正确的验证码");
            return view;
        }
        AdminUser adminUser = adminUserService.getAdminUser(user, password);
        if (adminUser == null) {
            view.addObject("message", "登录失败");
            return view;
        }
        session.setAttribute(Constants.USER, adminUser);
        view.setViewName("main");
        return view;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    @RequestMapping("/admin/main")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("main");
        return view;
    }
}
