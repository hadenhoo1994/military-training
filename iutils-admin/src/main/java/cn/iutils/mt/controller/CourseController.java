package cn.iutils.mt.controller;

import cn.iutils.common.ResultJson;
import cn.iutils.common.utils.UserUtils;
import cn.iutils.mt.entity.Project;
import cn.iutils.mt.service.ProjectService;
import cn.iutils.sys.entity.Organization;
import cn.iutils.sys.service.OrganizationService;
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
    @Autowired
    private OrganizationService organizationService;


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
    public String list(Model model, Page<Course> page, Course course) {
        Organization organization = new Organization();
        organization.setUser(UserUtils.getLoginUser());
        List<Organization> organizationList = organizationService.findList(organization);
        model.addAttribute("organizationList", organizationList);
        //初始化加载第一个
        if (JStringUtils.isBlank(course.getOrganizationId()) && organizationList.size() > 0) {
            course.setOrganizationId(organizationList.get(0).getId());
        }
        List<Course> courses = courseService.findPage(page, course);
        boolean flag = sortList(courses);
        if (flag) {
            model.addAttribute("organizationId", courses.get(1).getOrganizationId());
        }
        model.addAttribute("page", page.setList(courses));
        return "mt/course/list";
    }

    @RequiresPermissions("mt:course:view")
    @RequestMapping("/updateClasses")
    public String updateClasses(Model model, Page<Course> page, Course course) {
        Organization organization = new Organization();
        organization.setUser(UserUtils.getLoginUser());
        List<Organization> organizationList = organizationService.findList(organization);
        model.addAttribute("organizationList", organizationList);
        //初始化加载第一个
        if (JStringUtils.isBlank(course.getOrganizationId()) && organizationList.size() > 0) {
            course.setOrganizationId(organizationList.get(0).getId());
        }
        List<Course> courses = courseService.findPage(page, course);
        boolean flag = sortList(courses);
        if (flag) {
            model.addAttribute("organizationId", courses.get(1).getOrganizationId());
        }
        model.addAttribute("page", page.setList(courses));
        return "mt/course/update";
    }

    /**
     * 对集合进行排序
     *
     * @param courses
     */
    private Boolean sortList(List<Course> courses) {
        boolean flag = false;
        if (courses != null && courses.size() == 11) {
            //对集合进行排序
            Comparator comparator = (Comparator<Course>) (o1, o2) -> {
                if (o1.getDays() > o2.getDays()) {
                    return 1;
                } else {
                    return -1;
                }
            };
            courses.sort(comparator);
            flag = true;
        }
        return flag;
    }

//    /**
//     * 添加总数
//     *
//     * @param courses
//     */
//    public List<Course> setCourseCount(List<Course> courses) {
//        List<Course> courseList = new ArrayList<>();
//        for (Course c : courses) {
//            int i = courseService.getCountCourseProject(c.getId());
//            c.setClasses1(i);
//            courseList.add(c);
//        }
//        return courseList;
//    }

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

    /**
     * 设置排课
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setCourse")
    public String setCourse(String organizationId, Integer days, Integer classes, String projectName) {
        //1.根据节次和排id查询出来排课数据
        Course addCourse = Course.newBuilder().organizationId(organizationId).days(days).build();
        Course course = courseService.findOne(addCourse);
        //2.修改课程信息
        if (course != null) {
            switch (classes) {
                case 1:
                    course.setClasses1(projectName);
                    break;
                case 2:
                    course.setClasses2(projectName);
                    break;
                case 3:
                    course.setClasses3(projectName);
                    break;
                case 4:
                    course.setClasses4(projectName);
                    break;
                case 5:
                    course.setClasses5(projectName);
                    break;
                case 6:
                    course.setClasses6(projectName);
                    break;
                case 7:
                    course.setClasses7(projectName);
                    break;
                case 8:
                    course.setClasses8(projectName);
                    break;
                case 9:
                    course.setClasses9(projectName);
                    break;
                case 10:
                    course.setClasses10(projectName);
                    break;
                case 11:
                    course.setClasses11(projectName);
                    break;
            }
            courseService.save(course);
            return JsonUtil.toJson(ResultJson.successJson("200", "成功"));
        }
        return JsonUtil.toJson(ResultJson.failureJson("300", "失败"));

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
