package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Order;
import cn.meilituibian.web.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getOrders(Integer status) {
        return orderMapper.getOrders(status);
    }

    public int updateOrderStatus(Long id, int status) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("status", status);
        param.put("updateDate", new Date());
        return orderMapper.updateOrderStatus(param);
    }
}
