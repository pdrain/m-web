package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Category;
import cn.meilituibian.web.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> categoryList() {
        return categoryMapper.categoryList();
    }

    public List<Category> getCategoryListByGrade(Long grade) {
        return categoryMapper.getCategoryListByGrade(grade);
    }

    public List<Category> getUnFinishCateogryList() {
        return categoryMapper.getUnFinishCateogryList();
    }

    @Transactional
    public void save(Category category) {
        categoryMapper.save(category);
    }
}
