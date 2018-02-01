package cn.iutils.mt.controller;

import cn.iutils.common.ResultJson;
import cn.iutils.mt.entity.Project;
import cn.iutils.mt.service.ProjectService;
import com.mzlion.core.json.fastjson.JsonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.iutils.common.Page;
import cn.iutils.common.utils.JStringUtils;
import cn.iutils.common.BaseController;
import cn.iutils.mt.entity.Course;
import cn.iutils.mt.service.CourseService;

import java.util.*;

/**
 * 排课表 控制器
 *
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
    public String list(Model model, Page<Course> page,Course course) {
        List<Course> courses = courseService.findPage(page, course);
        if (courses.size()>0){
            courses =  setCourseCount(courses);
        }
        model.addAttribute("page", page.setList(courses));
        return "mt/course/list";
    }

    /**
     * 添加总数
     * @param courses
     */
    public List<Course> setCourseCount(List<Course> courses) {
        List<Course> courseList = new ArrayList<>();
        for (Course c :courses) {
           int i = courseService.getCountCourseProject(c.getId());
           c.setClasses1(i);
            courseList.add(c);
        }
        return courseList;
    }

    @RequiresPermissions("mt:course:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Course course, Model model) {
        model.addAttribute("course", course);
        return "mt/course/form";
    }

    @RequiresPermissions("mt:course:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Course course, RedirectAttributes redirectAttributes) {
        courseService.save(course);
        addMessage(redirectAttributes, "新增成功");
        return "redirect:" + adminPath + "/mt/course/update?id=" + course.getId();
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
        addMessage(redirectAttributes, "修改成功");
        return "redirect:" + adminPath + "/mt/course/update?id=" + course.getId();
    }

    @RequiresPermissions("mt:course:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        courseService.delete(id);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/mt/course?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }

    //获取周次
    @ResponseBody
    @RequestMapping(value = "/getWeek")
    public String getWeek(String platoonId) {
//        List<Course> courses = courseService.findList(Course.newBuilder().platoonId(Integer.valueOf(platoonId)).build());
        //获取20周
        List<Integer> weekList = new ArrayList<>();
        for (int i = 0; i <= 19; i++) {
            weekList.add(i + 1);
        }
//        if (courses.size() < 1) {
//            return JsonUtil.toJson(weekList);
//        }
//        Set<Integer> weeks = new HashSet<>();
//        //获取已有的周数
//        for (Course c : courses) {
//            weeks.add(c.getWeek());
//        }
//        Iterator<Integer> iterator = weeks.iterator();
//        //排除周数
//        while (iterator.hasNext()) {
//            Iterator<Integer> listIter = weekList.listIterator();
//            while (listIter.hasNext()) {
//                if (iterator.next().compareTo(listIter.next()) == 0) {
//                    listIter.remove();
//                }
//            }
//        }
        return JsonUtil.toJson(weekList);
    }



}
