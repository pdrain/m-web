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
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<AdminMenu> menuList(){
        List<AdminMenu> menus = adminMenuMapper.list();
        return menus;
    }

    public List<AdminMenu>  getMenusByUser(AdminUser adminUser){
        AdminRole adminRole = adminRoleMapper.getRoleById(adminUser.getRoleId());
        String menus = adminRole.getMenus();
        if (StringUtils.isEmpty(menus)) {
            return menusForAdmin();
        }
        List<AdminMenu> menuList = menuList();
        Map<Long, AdminMenu> menuMap = menuList.stream().collect(Collectors.toMap(AdminMenu::getId, Function.identity()));
        List<AdminMenu> result = new ArrayList<>();
        JSONArray menusJson = new JSONArray(menus);
        for (int i = 0; i < menusJson.length(); i++) {
            JSONObject menuObj = menusJson.getJSONObject(i);
            Long parent = menuObj.getLong("menu");
            String subStr = menuObj.getString("subs");
            if (org.apache.commons.lang3.StringUtils.isEmpty(subStr) || parent == null) {
                continue;
            }
            AdminMenu parentMenu = menuMap.get(parent);
            result.add(parentMenu);

            String[] subMenus = subStr.split(",");
            for (String id : subMenus) {
                if (org.apache.commons.lang3.StringUtils.isEmpty(id)) {
                    continue;
                }
                Long subMenuId = Long.parseLong(id);
                AdminMenu subAdminMenu = menuMap.get(subMenuId);
                if (subAdminMenu.getParent() == parent) {
                    parentMenu.getSubMenus().add(subAdminMenu);
                }
            }
        }
        return result;
    }


    private List<AdminMenu> menusForAdmin() {
        List<AdminMenu> list = menuList();
        List<AdminMenu> parents = list.stream().filter(s->s.getParent() == null).collect(Collectors.toList());
        for (AdminMenu menu : parents) {
            for (AdminMenu childMenu : list) {
                if (childMenu.getParent() == menu.getId()) {
                    menu.getSubMenus().add(childMenu);
                }
            }
        }
        return parents;
    }

    public  AdminUser getAdminUserById(Integer id){
        AdminUser  user= null;
        try{
            user = this.adminUserMapper.getAdminUserById(id);
        }catch (Exception ex){
            // TODO
            throw ex;
        }

        return user;
    }

    public List<AdminUser> getAllAdminUser(String user,Integer roleId){
        List<AdminUser>  userList= new ArrayList<>();
        try{
            userList = this.adminUserMapper.getAllAdminUser(user,roleId);
        }catch (Exception ex){
            // TODO
            throw ex;
        }

        return userList;
    }


    public  void addNewAdminUser(AdminUser adminUser){
        try{
            String md5Password = this.md5DigestAsHex(adminUser.getPassword());
            adminUser.setPassword(md5Password);
            this.adminUserMapper.addNewAdminUser(adminUser);
        }catch (Exception ex){
            // TODO
            throw ex;
        }
    }
}
