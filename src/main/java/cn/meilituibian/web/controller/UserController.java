package cn.meilituibian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView userList() {
        ModelAndView view = new ModelAndView("userList");
        view.setViewName("userList");
        return view;
    }
}
