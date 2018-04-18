package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> categoryList();

    List<Category> getCategoryListByGrade(Long grade);

    List<Category> getUnFinishCateogryList();
}
