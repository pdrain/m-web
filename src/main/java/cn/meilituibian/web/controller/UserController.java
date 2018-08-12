package cn.meilituibian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/user")
public class UserController  extends BaseController{
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView userList() {

        return this.viewResult("userList");
    }
}
