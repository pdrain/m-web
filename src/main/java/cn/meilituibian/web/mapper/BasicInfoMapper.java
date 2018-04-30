package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasicInfoMapper {
    void save(Information information);
    void updateBasiceInfo(Information information);

    List<Information> list();

    Information getInformationById(Long id);
}
