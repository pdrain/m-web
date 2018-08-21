package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.AdminUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    AdminUser getAdminUserByUser(String user);

    AdminUser getAdminUserById(Integer id);

    List<AdminUser> getAllAdminUser(String user, Integer roleId);

    void addNewAdminUser(AdminUser adminUser);

    void updateAdminUser(AdminUser adminUser);
}
