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
import cn.iutils.mt.entity.Classes;
import cn.iutils.mt.service.ClassesService;

/**
 * 班级表 控制器
 *
 * @author iutils.cn
 * @version 1.0
 */
@Controller
@RequestMapping("${adminPath}/mt/classes")
public class ClassesController extends BaseController {

    @Autowired
    private ClassesService classesService;

    @ModelAttribute
    public Classes get(@RequestParam(required = false) String id) {
        Classes entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = classesService.get(id);
        }
        if (entity == null) {
            entity = new Classes();
        }
        return entity;
    }

    @RequiresPermissions("mt:classes:view")
    @RequestMapping()
    public String list(Model model, Page<Classes> page) {
        model.addAttribute("page", page.setList(classesService.findPage(page, new Classes())));
        return "mt/classes/list";
    }

    @RequiresPermissions("mt:classes:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Classes classes, Model model) {
        model.addAttribute("classes", classes);
        return "mt/classes/form";
    }

    @RequiresPermissions("mt:classes:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Classes classes, RedirectAttributes redirectAttributes) {
        classesService.save(classes);
        addMessage(redirectAttributes, "新增成功");
        return "redirect:" + adminPath + "/mt/classes/update?id=" + classes.getId();
    }

    @RequiresPermissions("mt:classes:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Classes classes, Model model) {
        model.addAttribute("classes", classes);
        return "mt/classes/form";
    }

    @RequiresPermissions("mt:classes:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Classes classes, RedirectAttributes redirectAttributes) {
        classesService.save(classes);
        addMessage(redirectAttributes, "修改成功");
        return "redirect:" + adminPath + "/mt/classes/update?id=" + classes.getId();
    }

    @RequiresPermissions("mt:classes:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        classesService.delete(id);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/mt/classes?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }
}
