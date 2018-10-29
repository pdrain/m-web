package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> list();

    Product getProductById(Long id);
}
