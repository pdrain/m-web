package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.AdminMenu;
import cn.meilituibian.web.domain.AdminRole;
import cn.meilituibian.web.domain.AdminUser;
import cn.meilituibian.web.domain.MenuInfo;
import cn.meilituibian.web.mapper.AdminMenuMapper;
import cn.meilituibian.web.mapper.AdminRoleMapper;
import cn.meilituibian.web.mapper.AdminUserMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminMenuMapper adminMenuMapper;

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

    /*
     * 获取用户菜单
     */
    public List<MenuInfo> getMenusByUserName(String userName) {

        List<MenuInfo> menus = new ArrayList<>();
        MenuInfo menu = new MenuInfo();
        menu.setName("用户管理");
        List<MenuInfo> childs = new ArrayList<>();
        MenuInfo cMenu = new MenuInfo();
        cMenu.setName("用户列表");
        cMenu.setPath("/admin/user/list?m=1");
        childs.add(cMenu);
        menu.setChilds(childs);
        menus.add(menu);

        menu = new MenuInfo();
        menu.setName("菜单管理");
        List<MenuInfo> childs1 = new ArrayList<>();
        MenuInfo cMenu1 = new MenuInfo();
        cMenu1.setName("增加菜单");
        cMenu1.setPath("/admin/category/page?m=1");
        childs1.add(cMenu1);
        MenuInfo cMenu2 = new MenuInfo();
        cMenu2.setName("项目列表");
        cMenu2.setPath("/admin/project/list?m=1");
        childs1.add(cMenu2);
        MenuInfo cMenu3 = new MenuInfo();
        cMenu3.setName("增加详情");
        cMenu3.setPath("/admin/project/page?=1");
        childs1.add(cMenu3);
        menu.setChilds(childs1);
        menus.add(menu);

        menu = new MenuInfo();
        menu.setName("基本信息管理");
        List<MenuInfo> baseChilds1 = new ArrayList<>();
        MenuInfo basecMenu1 = new MenuInfo();
        basecMenu1.setName("增加基本信息");
        basecMenu1.setPath("/admin/info/page?m=2");
        baseChilds1.add(basecMenu1);
        MenuInfo basecMenu2 = new MenuInfo();
        basecMenu2.setName("文章列表");
        basecMenu2.setPath("/admin/articles?m=2");
        baseChilds1.add(basecMenu2);
        MenuInfo basecMenu3 = new MenuInfo();
        basecMenu3.setName("增加文章");
        basecMenu3.setPath("/admin/articles/add-page?m=2");
        baseChilds1.add(basecMenu3);
        menu.setChilds(baseChilds1);
        menus.add(menu);

        menu = new MenuInfo();
        menu.setName("业务管理");
        List<MenuInfo> busChilds = new ArrayList<>();
        MenuInfo busMenu1 = new MenuInfo();
        busMenu1.setName("业务员列表");
        busMenu1.setPath("/admin/client/list");
        busChilds.add(busMenu1);
        menu.setChilds(busChilds);
        menus.add(menu);

        menu = new MenuInfo();
        menu.setName("订单管理");
        List<MenuInfo> orderChilds = new ArrayList<>();
        MenuInfo ordMenu1 = new MenuInfo();
        ordMenu1.setName("业务员列表");
        ordMenu1.setPath("/admin/orders");
        orderChilds.add(ordMenu1);
        menu.setChilds(orderChilds);
        menus.add(menu);

        return menus;

    }

    public List<AdminMenu> list(){
        List<AdminMenu> menus = adminMenuMapper.list();
        return menus;
    }

    public List<AdminMenu>  getMenusByUser(AdminUser adminUser){
        AdminRole adminRole = adminRoleMapper.getRoleById(adminUser.getRoleId());
        String menus = adminRole.getMenus();
        if (StringUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }
        JSONArray menusJson = new JSONArray(menus);
        return null;
    }
}
