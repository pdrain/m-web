package cn.meilituibian.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.meilituibian.web.domain.WxUser;
import cn.meilituibian.web.service.WxUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.meilituibian.web.domain.Category;

@Controller
@RequestMapping("/admin/clients")
public class ClientsController{
	private static final int SALESMAN = -1;

	@Autowired
	private WxUserService wxUserService;

	@RequestMapping("/list")
	public ModelAndView userList(@RequestParam(value = "jobTitle", required = false) String jobTitle) {
		List<WxUser> clients = wxUserService.userList(StringUtils.isEmpty(jobTitle) ? null : Integer.parseInt(jobTitle));
		ModelAndView view = new ModelAndView("clientList");
		view.addObject("clients", clients);
		return view;
	}

	@RequestMapping("/performance")
	public ModelAndView performance(){
		ModelAndView view = new ModelAndView("clientPerformance");
		return view;
	}
}
