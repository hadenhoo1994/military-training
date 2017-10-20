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
import cn.iutils.mt.entity.Grade;
import cn.iutils.mt.service.GradeService;

/**
* 届表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/grade")
public class GradeController extends BaseController {

    @Autowired
    private GradeService gradeService;

    @ModelAttribute
    public Grade get(@RequestParam(required = false) String id) {
        Grade entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = gradeService.get(id);
        }
        if (entity == null) {
            entity = new Grade();
        }
        return entity;
    }

    @RequiresPermissions("mt:grade:view")
    @RequestMapping()
    public String list(Model model, Page<Grade> page) {
        model.addAttribute("page", page.setList(gradeService.findPage(page)));
        return "mt/grade/list";
    }

    @RequiresPermissions("mt:grade:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Grade grade,Model model) {
        model.addAttribute("grade", grade);
        return "mt/grade/form";
    }

    @RequiresPermissions("mt:grade:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Grade grade, RedirectAttributes redirectAttributes) {
        gradeService.save(grade);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/grade/update?id="+grade.getId();
    }

    @RequiresPermissions("mt:grade:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Grade grade, Model model) {
        model.addAttribute("grade", grade);
        return "mt/grade/form";
    }

    @RequiresPermissions("mt:grade:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Grade grade, RedirectAttributes redirectAttributes) {
        gradeService.save(grade);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/grade/update?id="+grade.getId();
    }

    @RequiresPermissions("mt:grade:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        gradeService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/grade?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
