package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<Order> getOrders(@Param("status") Integer status);

    int updateOrderStatus(Map<String, Object> param);
}
