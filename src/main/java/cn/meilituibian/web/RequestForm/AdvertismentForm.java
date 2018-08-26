package cn.meilituibian.web.RequestForm;

import cn.meilituibian.web.domain.Advertisment;

import java.util.List;

public class AdvertismentForm  {
    private String path;
    private String code;
    private Integer status;
    private List<Advertisment> advertismentList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Advertisment> getAdvertismentList() {
        return advertismentList;
    }

    public void setAdvertismentList(List<Advertisment> advertismentList) {
        this.advertismentList = advertismentList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
