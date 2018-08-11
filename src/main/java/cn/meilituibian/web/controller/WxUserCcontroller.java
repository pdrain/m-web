package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.WxUser;
import cn.meilituibian.web.service.WxUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class WxUserCcontroller {

    private static final int SALESMAN = -1;

    @Autowired
    private WxUserService wxUserService;

    @RequestMapping("/list")
    public ModelAndView userList(@RequestParam(value = "jobTitle", required = false) String jobTitle) {
        ModelAndView view = new ModelAndView("users");
        List<WxUser> users = wxUserService.userList(StringUtils.isEmpty(jobTitle) ? null : Integer.parseInt(jobTitle));
        view.addObject("users", users);
        return view;
    }
}
