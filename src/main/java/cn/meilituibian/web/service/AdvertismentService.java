package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Advertisment;
import cn.meilituibian.web.mapper.AdvertismentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdvertismentService {

    @Autowired
    private AdvertismentMapper advertismentMapper;

    @Value("${upload.filepath}")
    private String fileUploadPath;

    public void addNewAdvertisment(Advertisment advertisment) {
        try {
            this.advertismentMapper.addNewAdvertisment(advertisment);
        } catch (Exception ex) {
            //TODO
        }
    }

    public void updateAdvertisment(Advertisment advertisment) {
        try {
            this.advertismentMapper.updateAdvertisment(advertisment);
        } catch (Exception ex) {
            //TODO
        }
    }

    public List<Advertisment> getAdvertisment(Advertisment advertisment) {
        List<Advertisment> result = new ArrayList<>();
        try {
            result = this.advertismentMapper.getAdvertisment(advertisment);

        } catch (Exception e) {
            //TODO
            throw e;
        }
        return result;
    }

    public Advertisment getAdvertismentById(Integer id) {
        Advertisment result = null;
        try {
            this.advertismentMapper.getAdvertismentById(id);
        } catch (Exception e) {
            //TODO
            throw e;
        }
        return result;
    }

    public String saveImgOnDisk(String imgName, byte[] filedata) {
        String fileName = "";

        // 根据配置文件获取服务器图片存放路径
        String saveFilePath = fileUploadPath + "/uploadFile/";
        /* 构建文件目录 */
        File fileDir = new File(saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {
            //文件的扩张名
            String extensionName = imgName.substring(imgName.lastIndexOf(".") + 1);
            fileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
            String imgPath = saveFilePath + fileName;
            FileOutputStream out = new FileOutputStream(imgPath);
            // 写入文件
            out.write(filedata);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public void removeImg(String imgName) {
        String filePath = fileUploadPath + "/uploadFile/" + imgName;

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

    }

    public void saveAdvs(List<Advertisment> advertismentList) {
        try {
            this.advertismentMapper.addAdvertismentList(advertismentList);
        } catch (Exception ex) {
            for(Advertisment item:advertismentList){
                this.removeImg(item.getPath());
            }

            throw ex;
        }
    }
}
