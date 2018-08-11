package cn.meilituibian.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.meilituibian.web.domain.Category;

@Controller
@RequestMapping("/admin/client")
public class ClientsController {
	 @RequestMapping(value = "list", method = RequestMethod.GET)
	    public ModelAndView list() {
	       
	        ModelAndView view = new ModelAndView("clientList");
	        view.addObject("clientList");
	        return view;
	    }
}
