package cn.meilituibian.web.domain;


import java.util.List;
import java.util.Map;

public class MenuInfo {
    public String name;
    public String path;
    public List<MenuInfo> childs;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MenuInfo> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuInfo> childs) {
        this.childs = childs;
    }
}
