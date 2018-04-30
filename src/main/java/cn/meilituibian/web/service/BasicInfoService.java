package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Information;
import cn.meilituibian.web.mapper.BasicInfoMapper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BasicInfoService {
    @Autowired
    private BasicInfoMapper basicInfoMapper;

    @Transactional
    public void saveBasiceInfo(Information information) {
        if (information.getStatus() == null) {
            information.setStatus(0);
        }
        information.setContent(StringEscapeUtils.escapeHtml(information.getContent()));
        basicInfoMapper.save(information);
    }

    public List<Information> list() {
        return basicInfoMapper.list();
    }

    public Information getInformationById(Long id) {
        Information information = basicInfoMapper.getInformationById(id);
        information.setContent(StringEscapeUtils.unescapeHtml(information.getContent()));
        return information;
    }

    @Transactional
    public void updateBasiceInfo(Information information) {
        if (information.getStatus() == null) {
            information.setStatus(0);
        }
        basicInfoMapper.updateBasiceInfo(information);
    }
}
