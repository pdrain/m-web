package cn.meilituibian.web.ResponseObject;

import cn.meilituibian.web.domain.Advertisment;

import java.util.List;

public class AdvertismentListResp {
    private String code;
    private String name;
    private Integer status;
    private List<Advertisment> advPaths;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Advertisment> getAdvPaths() {
        return advPaths;
    }

    public void setAdvPaths(List<Advertisment> advPaths) {
        this.advPaths = advPaths;
    }
}
