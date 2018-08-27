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
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Advertisment getAdvertismentDetail(Integer id) {
        Advertisment result = new Advertisment();
        try {
            result = this.advertismentMapper.getAdvertismentById(id);
        } catch (Exception ex) {
            //TODO
            throw ex;
        }
        return result;
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

//    public Advertisment getAdvertismentById(Integer id) {
//        Advertisment result = null;
//        try {
//            this.advertismentMapper.getAdvertismentById(id);
//        } catch (Exception e) {
//            //TODO
//            throw e;
//        }
//        return result;
//    }

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

    public void removeImg(Integer id, String imgName) {
        this.removeImg(imgName);

        if (id > 0) {
            this.advertismentMapper.removeAdvertisment(id);
        }

    }

    public void saveAdvs(Advertisment advertisment) {
        try {

            if (advertisment.getId() > 0) {
                this.advertismentMapper.updateAdvertisment(advertisment);
            } else {
                this.advertismentMapper.addNewAdvertisment(advertisment);
            }

        } catch (Exception ex) {

            if (advertisment.getId() == 0) {
                this.removeImg(advertisment.getPath());
            }

            throw ex;
        }
    }

    public void onLineAdvertisment(Integer id) {
        try {
            Map<String,Object> args = new HashMap<>();
            args.put("id",id);
            args.put("status",1);
            this.advertismentMapper.updateAdvertisMentStatus(args);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void offLineAdvertisment(Integer id) {
        try {
            Map<String,Object> args = new HashMap<>();
            args.put("id",id);
            args.put("status",0);
            this.advertismentMapper.updateAdvertisMentStatus(args);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deleteAdvertisment(Integer id,String path) {
        try {
            this.advertismentMapper.deleteAdvertisment(id);
            this.removeImg(path);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
