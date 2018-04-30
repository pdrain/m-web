package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Information;
import cn.meilituibian.web.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/info")
public class BasicInfoController {
    @Autowired
    private BasicInfoService basicInfoService;
    @RequestMapping("/page")
    public ModelAndView page() {
        List<Information> list = basicInfoService.list();
        ModelAndView view =  new ModelAndView("infoAdd");
        view.addObject("list", list);
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView page(Information information) {
        basicInfoService.saveBasiceInfo(information);
        ModelAndView view =  new ModelAndView("infoAdd");
        List<Information> list = basicInfoService.list();
        view.addObject("list", list);
        return view;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(Information information) {
        basicInfoService.updateBasiceInfo(information);
        ModelAndView view =  new ModelAndView("infoAdd");
        List<Information> list = basicInfoService.list();
        view.addObject("list", list);
        return view;
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {
        ModelAndView view =  new ModelAndView("infoDetail");
        Information information = basicInfoService.getInformationById(id);
        view.addObject("information", information);
        List<Information> list = basicInfoService.list();
        view.addObject("list", list);
        return view;
    }
}
