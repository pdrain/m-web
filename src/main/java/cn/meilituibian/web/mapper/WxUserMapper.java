package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WxUserMapper {
    List<WxUser> userList(@Param("jobTitle") Integer jobTitle);
    WxUser getWxUserById(@Param("userId") Long userId);
}
