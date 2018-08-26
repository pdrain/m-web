package cn.meilituibian.web.controller;

import cn.meilituibian.web.RequestForm.AdvertismentForm;
import cn.meilituibian.web.domain.Advertisment;
import cn.meilituibian.web.domain.ResultObject;
import cn.meilituibian.web.domain.ResultStatus;
import cn.meilituibian.web.service.AdvertismentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
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
            } else {
                throw new Exception("文件类型错误，仅支持jpg、png、gif；");
            }

        } catch (Exception e) {
            result.setCode(ResultStatus.ERROR);
            result.setMsg(e.getMessage());
        }
        return result;

    }

    @RequestMapping(value = "remove_img", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject removeImg(@RequestBody AdvertismentForm advertismentForm) {
        ResultObject result = new ResultObject();
        try {
            this.advertismentService.removeImg(advertismentForm.getPath());
            result.setResult("");
            result.setCode(ResultStatus.SUCESS);
        } catch (Exception e) {
            result.setCode(ResultStatus.ERROR);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject saveAdvs(@RequestBody AdvertismentForm advertismentForm) {
        ResultObject result = new ResultObject();
        try {
            List<Advertisment> list = new ArrayList<>();
            for(Advertisment item:advertismentForm.getAdvertismentList()){
                item.setCode(advertismentForm.getCode());
                item.setStatus(0);
                list.add(item);
            }
            this.advertismentService.saveAdvs(list);
            List<Advertisment> advList = this.advertismentService.getAdvertisment(new Advertisment());
            result.setResult(advList);
            result.setCode(ResultStatus.SUCESS);
        } catch (Exception e) {
            result.setCode(ResultStatus.ERROR);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "get_adv_list", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject getAdvs(@RequestBody AdvertismentForm advertismentForm) {
        ResultObject result = new ResultObject();
        try {
            Advertisment  advertisment = new Advertisment();
            advertisment.setCode(advertismentForm.getCode());
            advertisment.setStatus(advertismentForm.getStatus());
            List<Advertisment> advList = this.advertismentService.getAdvertisment(advertisment);
            result.setResult(advList);
            result.setCode(ResultStatus.SUCESS);
        } catch (Exception e) {
            result.setCode(ResultStatus.ERROR);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
