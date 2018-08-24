package cn.meilituibian.web.controller;

import cn.meilituibian.web.domain.ResultObject;
import cn.meilituibian.web.domain.ResultStatus;
import cn.meilituibian.web.service.AdvertismentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;


@Controller
@RequestMapping("/admin/adv")
public class AdvertismentController extends BaseController {

    @Autowired
    private AdvertismentService advertismentService;

    @RequestMapping("/list")
    public ModelAndView advertisMentList() {
        return this.viewResult("advertisment");
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject uploadFile(@RequestParam("file") MultipartFile file) {
        ResultObject result = new ResultObject();
        try {
            String filename = file.getOriginalFilename();
            if (filename.endsWith("jpg") || filename.endsWith("png") || filename.endsWith("gif")) {
                String prefix = filename.substring(filename.lastIndexOf("."));
                String imgName = UUID.randomUUID().toString() + prefix;

                String imgUri = advertismentService.saveImgOnDisk(imgName, file.getBytes());

                result.setResult(imgUri);
                result.setCode(ResultStatus.SUCESS);
            }
        } catch (Exception e) {
            result.setCode(ResultStatus.ERROR);
            result.setMsg(e.getMessage());
        }
        return result;

    }
}
