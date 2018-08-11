package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.AdminUser;
import cn.meilituibian.web.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    public AdminUser getAdminUser(String user, String password) {
        String md5Password = md5DigestAsHex(password);
        AdminUser adminUser = adminUserMapper.getAdminUserByUser(user);
        if (adminUser == null || !md5Password.equalsIgnoreCase(adminUser.getPassword())) {
            return null;
        }
        return adminUser;
    }

    public String md5DigestAsHex(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("aq123eds".getBytes()));
    }
}
