package cn.meilituibian.web.domain;

import java.io.Serializable;

public class AdminMenu implements Serializable {
    private Long id;
    private String menuName;
    private Long parent;
    private String path;

    public AdminMenu(){}

    public AdminMenu(Long id, String menuName, Long parent, String path) {
        this.id = id;
        this.menuName = menuName;
        this.parent = parent;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
