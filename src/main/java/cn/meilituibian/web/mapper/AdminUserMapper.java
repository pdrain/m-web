package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper {
    AdminUser getAdminUserByUser(String user);
}
