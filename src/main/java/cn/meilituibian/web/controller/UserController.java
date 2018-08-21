package cn.meilituibian.web.controller;

import cn.meilituibian.web.RequestForm.AddAdminUserForm;
import cn.meilituibian.web.RequestForm.GetAllAdminUserListForm;
import cn.meilituibian.web.RequestForm.GetAdminUserByIdForm;
import cn.meilituibian.web.domain.AdminUser;
import cn.meilituibian.web.domain.ResultObject;
import cn.meilituibian.web.domain.ResultStatus;
import cn.meilituibian.web.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController  extends BaseController{

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView userList() {

        return this.viewResult("userList");
    }

    @RequestMapping(value = "add-and-edit", method = RequestMethod.GET)
    public ModelAndView menageUser() {

        return this.viewResult("manageAdminUser");
    }

    @RequestMapping(value = "get_all_userlist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultObject getAllUserList(@RequestBody GetAllAdminUserListForm getAllAdminUserListForm){
        ResultObject result = new ResultObject();
        try{
            System.out.println(getAllAdminUserListForm);
            List<AdminUser> list = this.adminUserService.getAllAdminUser(getAllAdminUserListForm.getUser(),getAllAdminUserListForm.getRoleId());
            result.setCode(ResultStatus.SUCESS);
            result.setResult(list);
        }catch (Exception ex){
            result.setCode(ResultStatus.ERROR);
            result.setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "add_admin_user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultObject addAdminUser(@RequestBody AddAdminUserForm addAdminUserForm){
        ResultObject result = new ResultObject();
        try {
            Long userId = addAdminUserForm.getId();
            if(userId.equals(0)){
                this.adminUserService.addNewAdminUser(addAdminUserForm);
            }else{
                this.adminUserService.updateAdminUser(addAdminUserForm);
            }

            result.setCode(ResultStatus.SUCESS);
        } catch (Exception ex) {
            result.setCode(ResultStatus.ERROR);
            result.setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "get_admin_user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultObject getAdminUser(@RequestBody GetAdminUserByIdForm getAdminUserByIdForm){
        ResultObject result = new ResultObject();
        try {
            AdminUser user = this.adminUserService.getAdminUserById(getAdminUserByIdForm.getId());
            result.result = user;
            result.setCode(ResultStatus.SUCESS);
        } catch (Exception ex) {
            result.setCode(ResultStatus.ERROR);
            result.setMsg(ex.getMessage());
        }
        return result;
    }
}
