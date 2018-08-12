package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Information;
import cn.meilituibian.web.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/info")
public class BasicInfoController  extends BaseController{
    @Autowired
    private BasicInfoService basicInfoService;
    @RequestMapping("/page")
    public ModelAndView page() {
        List<Information> list = basicInfoService.list();

        Map<String,Object> models = new HashMap<>();
        models.put("list",list);

        return this.viewResult("infoAdd",models);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView page(Information information) {
        basicInfoService.saveBasiceInfo(information);
        List<Information> list = basicInfoService.list();

        Map<String,Object> models = new HashMap<>();
        models.put("list",list);

        return this.viewResult("infoAdd",models);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(Information information) {
        basicInfoService.updateBasiceInfo(information);

        List<Information> list = basicInfoService.list();

        Map<String,Object> models = new HashMap<>();
        models.put("list",list);

        return this.viewResult("infoAdd",models);
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {

        Information information = basicInfoService.getInformationById(id);
        List<Information> list = basicInfoService.list();

        Map<String,Object> models = new HashMap<>();
        models.put("list",list);
        models.put("information",information);

        return this.viewResult("infoDetail",models);
    }
}
