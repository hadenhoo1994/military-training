package cn.iutils.mt.controller;

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
import cn.iutils.mt.entity.Profession;
import cn.iutils.mt.service.ProfessionService;

/**
* 专业表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/profession")
public class ProfessionController extends BaseController {

    @Autowired
    private ProfessionService professionService;

    @ModelAttribute
    public Profession get(@RequestParam(required = false) String id) {
        Profession entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = professionService.get(id);
        }
        if (entity == null) {
            entity = new Profession();
        }
        return entity;
    }

    @RequiresPermissions("mt:profession:view")
    @RequestMapping()
    public String list(Model model, Page<Profession> page) {
        model.addAttribute("page", page.setList(professionService.findPage(page)));
        return "mt/profession/list";
    }

    @RequiresPermissions("mt:profession:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Profession profession,Model model) {
        model.addAttribute("profession", profession);
        return "mt/profession/form";
    }

    @RequiresPermissions("mt:profession:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Profession profession, RedirectAttributes redirectAttributes) {
        professionService.save(profession);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/profession/update?id="+profession.getId();
    }

    @RequiresPermissions("mt:profession:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Profession profession, Model model) {
        model.addAttribute("profession", profession);
        return "mt/profession/form";
    }

    @RequiresPermissions("mt:profession:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Profession profession, RedirectAttributes redirectAttributes) {
        professionService.save(profession);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/profession/update?id="+profession.getId();
    }

    @RequiresPermissions("mt:profession:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        professionService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/profession?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
