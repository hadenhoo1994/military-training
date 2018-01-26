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
import cn.iutils.mt.entity.Course;
import cn.iutils.mt.service.CourseService;

/**
* 排课表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/course")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    @ModelAttribute
    public Course get(@RequestParam(required = false) String id) {
        Course entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = courseService.get(id);
        }
        if (entity == null) {
            entity = new Course();
        }
        return entity;
    }

    @RequiresPermissions("mt:course:view")
    @RequestMapping()
    public String list(Model model, Page<Course> page) {
        model.addAttribute("page", page.setList(courseService.findPage(page,new Course())));
        return "mt/course/list";
    }

    @RequiresPermissions("mt:course:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Course course,Model model) {
        model.addAttribute("course", course);
        return "mt/course/form";
    }

    @RequiresPermissions("mt:course:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Course course, RedirectAttributes redirectAttributes) {
        courseService.save(course);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/course/update?id="+course.getId();
    }

    @RequiresPermissions("mt:course:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Course course, Model model) {
        model.addAttribute("course", course);
        return "mt/course/form";
    }

    @RequiresPermissions("mt:course:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Course course, RedirectAttributes redirectAttributes) {
        courseService.save(course);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/course/update?id="+course.getId();
    }

    @RequiresPermissions("mt:course:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        courseService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/course?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
