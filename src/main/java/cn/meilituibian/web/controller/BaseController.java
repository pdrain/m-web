package cn.meilituibian.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BaseController{

    @Autowired
    HttpServletRequest request; //这里可以获取到request


    public  ModelAndView viewResult(){

        ModelAndView view = new ModelAndView("index");

        return view;
    }

    public  ModelAndView viewResult(String viewName){
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }



    public  ModelAndView viewResult(String viewName, Map<String,Object> models){

        ModelAndView view = new ModelAndView(viewName);


        for (Map.Entry<String, Object> entry : models.entrySet()) {
            view.addObject(entry.getKey(), entry.getValue());
        }

        return view;
    }
}
