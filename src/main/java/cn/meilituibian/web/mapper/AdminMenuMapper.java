package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.AdminMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMenuMapper {
    public List<AdminMenu> list();
}
