package cn.meilituibian.web.controller;

import cn.meilituibian.web.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        ModelAndView view = new ModelAndView();
        HttpSession session = request.getSession();
        Object captcha = session.getAttribute(Constants.CAPTCHA_CODE);
        if (captcha == null || !captcha.toString().equalsIgnoreCase(code)) {
            view.setViewName("index");
            view.addObject("message", "请输入正确的验证码");
            return view;
        }
        view.setViewName("main");
        return view;
    }

    @RequestMapping("/main")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("main");
        return view;
    }
}
