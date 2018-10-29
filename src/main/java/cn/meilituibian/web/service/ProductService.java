package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Product;
import cn.meilituibian.web.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> list() {
        return productMapper.list();
    }

    public Product getProductById(Long id) {
        return productMapper.getProductById(id);
    }
}
