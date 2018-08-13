package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.AdminRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRoleMapper {
    AdminRole getRoleById(Long id);
}
