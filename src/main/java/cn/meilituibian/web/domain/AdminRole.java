package cn.meilituibian.web.domain;

import java.io.Serializable;

public class AdminRole implements Serializable{
    private Long id;
    private String roleName;
    private String roleCode;
    private String menus;

    public AdminRole(){}

    public AdminRole(Long id, String roleName, String roleCode, String menus) {
        this.id = id;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.menus = menus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }
}
