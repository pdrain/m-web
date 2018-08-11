package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.WxUser;
import cn.meilituibian.web.mapper.WxUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.List;

@Service
public class WxUserService {
    @Autowired
    private WxUserMapper wxUserMapper;

    public List<WxUser> userList(Integer jobTitle) {
        return wxUserMapper.userList(jobTitle);
    }
}
