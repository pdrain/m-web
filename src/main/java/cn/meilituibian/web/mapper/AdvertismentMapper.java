package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.Advertisment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdvertismentMapper {
    void addNewAdvertisment(Advertisment advertisment);

    void updateAdvertisment(Advertisment advertisment);

    void removeAdvertisment(Integer id);

    List<Advertisment> getAdvertisment(Advertisment advertisment);

    Advertisment getAdvertismentById(Integer id);

    void updateAdvertisMentStatus(Map<String,Object> args);

    void deleteAdvertisment(Integer id);

}
