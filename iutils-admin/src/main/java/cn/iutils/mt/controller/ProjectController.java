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
import cn.iutils.mt.entity.Project;
import cn.iutils.mt.service.ProjectService;

/**
* 军训项目表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @ModelAttribute
    public Project get(@RequestParam(required = false) String id) {
        Project entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = projectService.get(id);
        }
        if (entity == null) {
            entity = new Project();
        }
        return entity;
    }

    @RequiresPermissions("mt:project:view")
    @RequestMapping()
    public String list(Model model, Page<Project> page) {
        model.addAttribute("page", page.setList(projectService.findPage(page)));
        return "mt/project/list";
    }

    @RequiresPermissions("mt:project:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Project project,Model model) {
        model.addAttribute("project", project);
        return "mt/project/form";
    }

    @RequiresPermissions("mt:project:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Project project, RedirectAttributes redirectAttributes) {
        projectService.save(project);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/project/update?id="+project.getId();
    }

    @RequiresPermissions("mt:project:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Project project, Model model) {
        model.addAttribute("project", project);
        return "mt/project/form";
    }

    @RequiresPermissions("mt:project:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Project project, RedirectAttributes redirectAttributes) {
        projectService.save(project);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/project/update?id="+project.getId();
    }

    @RequiresPermissions("mt:project:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        projectService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/project?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
