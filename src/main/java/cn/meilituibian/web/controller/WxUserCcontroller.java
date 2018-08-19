package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.WxUser;
import cn.meilituibian.web.service.WxUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/sales")
public class WxUserCcontroller extends BaseController{

    private static final int SALESMAN = -1;

    @Autowired
    private WxUserService wxUserService;

    @RequestMapping("/list")
    public ModelAndView userList(@RequestParam(value = "jobTitle", required = false) String jobTitle) {
        List<WxUser> users = wxUserService.userList(StringUtils.isEmpty(jobTitle) ? null : Integer.parseInt(jobTitle));
        Map<String,Object> models = new HashMap<>();
        models.put("users", users);
        return this.viewResult("users",models);
    }
}
