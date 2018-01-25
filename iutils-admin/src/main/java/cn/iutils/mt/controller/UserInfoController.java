package cn.iutils.mt.controller;

import cn.iutils.common.utils.sequence.IdWorker;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.iutils.common.Page;
import cn.iutils.common.utils.JStringUtils;
import cn.iutils.common.BaseController;
import cn.iutils.mt.entity.UserInfo;
import cn.iutils.mt.service.UserInfoService;

/**
* 用户表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @ModelAttribute
    public UserInfo get(@RequestParam(required = false) String id) {
        UserInfo entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = userInfoService.get(id);
        }
        if (entity == null) {
            entity = new UserInfo();
        }
        return entity;
    }

    @RequiresPermissions("mt:userInfo:view")
    @RequestMapping()
    public String list(Model model, Page<UserInfo> page) {
        model.addAttribute("page", page.setList(userInfoService.findPage(page,new UserInfo())));
        return "mt/userInfo/list";
    }

    @RequiresPermissions("mt:userInfo:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(UserInfo userInfo,Model model) {
        model.addAttribute("userInfo", userInfo);
        return "mt/userInfo/form";
    }

    @RequiresPermissions("mt:userInfo:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserInfo userInfo, RedirectAttributes redirectAttributes) {
        userInfo.setNumber(IdWorker.getInstance().nextId());
        userInfoService.save(userInfo);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/userInfo/update?id="+userInfo.getId();
    }

    @RequiresPermissions("mt:userInfo:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(UserInfo userInfo, Model model) {
        model.addAttribute("userInfo", userInfo);
        return "mt/userInfo/form";
    }

    @RequiresPermissions("mt:userInfo:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(UserInfo userInfo, RedirectAttributes redirectAttributes) {
        userInfoService.save(userInfo);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/userInfo/update?id="+userInfo.getId();
    }

    @RequiresPermissions("mt:userInfo:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        userInfoService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/userInfo?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
