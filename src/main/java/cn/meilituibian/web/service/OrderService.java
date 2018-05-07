package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Order;
import cn.meilituibian.web.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getOrders(Integer status) {
        return orderMapper.getOrders(status);
    }
}
