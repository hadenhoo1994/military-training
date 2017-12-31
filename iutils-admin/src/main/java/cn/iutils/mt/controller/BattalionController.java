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
import cn.iutils.mt.entity.Battalion;
import cn.iutils.mt.service.BattalionService;

/**
* 营表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/battalion")
public class BattalionController extends BaseController {

    @Autowired
    private BattalionService battalionService;

    @ModelAttribute
    public Battalion get(@RequestParam(required = false) String id) {
        Battalion entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = battalionService.get(id);
        }
        if (entity == null) {
            entity = new Battalion();
        }
        return entity;
    }

    @RequiresPermissions("mt:battalion:view")
    @RequestMapping()
    public String list(Model model, Page<Battalion> page) {
        model.addAttribute("page", page.setList(battalionService.findPage(page,new Battalion())));
        return "mt/battalion/list";
    }

    @RequiresPermissions("mt:battalion:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Battalion battalion,Model model) {
        model.addAttribute("battalion", battalion);
        return "mt/battalion/form";
    }

    @RequiresPermissions("mt:battalion:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Battalion battalion, RedirectAttributes redirectAttributes) {
        battalionService.save(battalion);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/battalion/update?id="+battalion.getId();
    }

    @RequiresPermissions("mt:battalion:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Battalion battalion, Model model) {
        model.addAttribute("battalion", battalion);
        return "mt/battalion/form";
    }

    @RequiresPermissions("mt:battalion:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Battalion battalion, RedirectAttributes redirectAttributes) {
        battalionService.save(battalion);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/battalion/update?id="+battalion.getId();
    }

    @RequiresPermissions("mt:battalion:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        battalionService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/battalion?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
