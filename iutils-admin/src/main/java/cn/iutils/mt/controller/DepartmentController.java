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
import cn.iutils.mt.entity.Department;
import cn.iutils.mt.service.DepartmentService;

/**
* 系表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/department")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute
    public Department get(@RequestParam(required = false) String id) {
        Department entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = departmentService.get(id);
        }
        if (entity == null) {
            entity = new Department();
        }
        return entity;
    }

    @RequiresPermissions("mt:department:view")
    @RequestMapping()
    public String list(Model model, Page<Department> page) {
        model.addAttribute("page", page.setList(departmentService.findPage(page,new Department())));
        return "mt/department/list";
    }

    @RequiresPermissions("mt:department:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Department department,Model model) {
        model.addAttribute("department", department);
        return "mt/department/form";
    }

    @RequiresPermissions("mt:department:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Department department, RedirectAttributes redirectAttributes) {
        departmentService.save(department);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/department/update?id="+department.getId();
    }

    @RequiresPermissions("mt:department:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Department department, Model model) {
        model.addAttribute("department", department);
        return "mt/department/form";
    }

    @RequiresPermissions("mt:department:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Department department, RedirectAttributes redirectAttributes) {
        departmentService.save(department);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/department/update?id="+department.getId();
    }

    @RequiresPermissions("mt:department:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        departmentService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/department?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
