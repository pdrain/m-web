package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.Order;
import cn.meilituibian.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/orders")
public class OrderController  extends BaseController{
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getOrders(@RequestParam(value = "status", required = false) Integer status) {
        List<Order> list = orderService.getOrders(status);

        Map<String,Object> models = new HashMap<>();
        models.put("list",list);

        return this.viewResult("orderList",models);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateOrderStatus(@PathVariable Long id, int status) {
        orderService.updateOrderStatus(id, status);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}
