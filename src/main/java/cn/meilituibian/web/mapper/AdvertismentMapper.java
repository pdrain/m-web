package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.Advertisment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertismentMapper {
    void addNewAdvertisment(Advertisment advertisment);

    void updateAdvertisment(Advertisment advertisment);

    List<Advertisment> getAdvertisment(Advertisment advertisment);

    Advertisment getAdvertismentById(Integer id);

    Integer  addAdvertismentList(List<Advertisment> advertismentList);

}
