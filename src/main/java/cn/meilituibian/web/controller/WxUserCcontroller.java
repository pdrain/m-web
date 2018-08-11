package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.WxUser;
import cn.meilituibian.web.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class WxUserCcontroller {

    private static final int SALESMAN = 1;

    @Autowired
    private WxUserService wxUserService;

    @RequestMapping("/list")
    public ModelAndView userList() {
        ModelAndView view = new ModelAndView("users");
        List<WxUser> users = wxUserService.userList(SALESMAN);
        view.addObject("users", users);
        return view;
    }
}
