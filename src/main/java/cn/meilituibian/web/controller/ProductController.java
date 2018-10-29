package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Product;
import cn.meilituibian.web.mapper.ProductMapper;
import cn.meilituibian.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add() {

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView view = new ModelAndView();
        List<Product> list = productService.list();
        view.addObject("list", list);
        view.setViewName("productList");
        return view;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("id") Long id) {
        Product product = productService.getProductById(id);
        ModelAndView view = new ModelAndView("productEdit");
        view.addObject("product", product);
        return view;
    }
}
