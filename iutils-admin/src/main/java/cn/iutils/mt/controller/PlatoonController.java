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
import cn.iutils.mt.entity.Platoon;
import cn.iutils.mt.service.PlatoonService;

/**
* 军排表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/platoon")
public class PlatoonController extends BaseController {

    @Autowired
    private PlatoonService platoonService;

    @ModelAttribute
    public Platoon get(@RequestParam(required = false) String id) {
        Platoon entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = platoonService.get(id);
        }
        if (entity == null) {
            entity = new Platoon();
        }
        return entity;
    }

    @RequiresPermissions("mt:platoon:view")
    @RequestMapping()
    public String list(Model model, Page<Platoon> page) {
        model.addAttribute("page", page.setList(platoonService.findPage(page,new Platoon())));
        return "mt/platoon/list";
    }

    @RequiresPermissions("mt:platoon:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Platoon platoon,Model model) {
        model.addAttribute("platoon", platoon);
        return "mt/platoon/form";
    }

    @RequiresPermissions("mt:platoon:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Platoon platoon, RedirectAttributes redirectAttributes) {
        platoonService.save(platoon);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/platoon/update?id="+platoon.getId();
    }

    @RequiresPermissions("mt:platoon:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Platoon platoon, Model model) {
        model.addAttribute("platoon", platoon);
        return "mt/platoon/form";
    }

    @RequiresPermissions("mt:platoon:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Platoon platoon, RedirectAttributes redirectAttributes) {
        platoonService.save(platoon);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/platoon/update?id="+platoon.getId();
    }

    @RequiresPermissions("mt:platoon:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        platoonService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/platoon?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
