package cn.iutils.mt.controller;

import cn.iutils.common.utils.UserUtils;
import cn.iutils.mt.entity.Course;
import cn.iutils.mt.entity.Project;
import cn.iutils.mt.service.CourseService;
import cn.iutils.mt.service.ProjectService;
import cn.iutils.sys.entity.Organization;
import cn.iutils.sys.entity.User;
import cn.iutils.sys.service.OrganizationService;
import cn.iutils.sys.service.UserService;
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
import cn.iutils.mt.entity.Achievement;
import cn.iutils.mt.service.AchievementService;

import java.util.*;

/**
 * 成绩表 控制器
 *
 * @author iutils.cn
 * @version 1.0
 */
@Controller
@RequestMapping("${adminPath}/mt/achievement")
public class AchievementController extends BaseController {

    @Autowired
    private AchievementService achievementService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ProjectService projectService;

    @ModelAttribute
    public Achievement get(@RequestParam(required = false) String id) {
        Achievement entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = achievementService.get(id);
        }
        if (entity == null) {
            entity = new Achievement();
        }
        return entity;
    }

    @RequiresPermissions("mt:achievement:view")
    @RequestMapping()
    public String list(User user, Model model, Page<User> page) {
        Organization organization = new Organization();
        organization.setUser(UserUtils.getLoginUser());
        List<Organization> organizationList = organizationService.findList(organization);
        model.addAttribute("organizationList", organizationList);
        //初始化加载第一个
        if (JStringUtils.isBlank(user.getOrganizationId()) && organizationList.size() > 0) {
            user.setOrganizationId(organizationList.get(0).getId());
        }
        page.setEntity(user);
        model.addAttribute("page", page.setList(userService.findPage(page)));
        return "mt/achievement/list";
    }

    @RequestMapping(value = "/achievementList", method = RequestMethod.GET)
    public String achievementList(Achievement achievement, Model model, String userId) {
        User user = userService.get(userId);        //根据userId获取用户
        if (user == null) {
            return "mt/achievement/list";       //返回当前页
        }
        List<Course> courses = courseService.findList(Course.newBuilder().organizationId(user.getOrganizationId()).build());    //查询学生所属排的排课信息
        if (courses != null && courses.size() > 0) {
            Set<String> objectNames = new HashSet<>();
            for (Course c : courses) {
                objectNames.add(c.getClasses1());
                objectNames.add(c.getClasses2());
                objectNames.add(c.getClasses3());
                objectNames.add(c.getClasses4());
                objectNames.add(c.getClasses5());
            }
            List<Project> projectList = new ArrayList<>();      //创建一个集合放置课程信息
            //所有的课程都放入了这个set集合中了,根据名字获取所有的课程
            Iterator iterator = objectNames.iterator();
            while (iterator.hasNext()) {
                Project projectadd = new Project();
                projectadd.setProjectName(String.valueOf(iterator.next()));
                Project project = projectService.findOne(projectadd);
                projectList.add(project);       //放入集合中
            }
            for (Project p : projectList) {      //遍历课程信息
                Achievement achievementAdd = achievementService.findOne(Achievement.newBuilder()
                        .userId(Integer.valueOf(userId)).projectId(Integer.valueOf(p.getId())).build());
                if (achievementAdd == null) {
                    //该课程不在 创建一个新的该门课的成绩管理
                    Achievement a = Achievement.newBuilder()
                            .userId(Integer.valueOf(user.getId()))
                            .userName(user.getName())
                            .projectId(Integer.valueOf(p.getId()))
                            .projectName(p.getProjectName()).build();
                    achievementService.save(a);
                }
            }
        }else{
            return "mt/achievement/list";       //返回当前页
        }
        List<Achievement> achievementList = achievementService.findList(Achievement.newBuilder().userId(Integer.valueOf(user.getId())).build());
        model.addAttribute("achievementList", achievementList);
        model.addAttribute("achievement", achievement);
        return "mt/achievement/aList";
    }


    @RequiresPermissions("mt:achievement:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Achievement achievement, Model model) {
        model.addAttribute("achievement", achievement);
        return "mt/achievement/form";
    }

    @RequiresPermissions("mt:achievement:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Achievement achievement, RedirectAttributes redirectAttributes) {
        achievementService.save(achievement);
        addMessage(redirectAttributes, "新增成功");
        return "redirect:" + adminPath + "/mt/achievement/update?id=" + achievement.getId();
    }

    @RequiresPermissions("mt:achievement:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Achievement achievement, Model model) {
        model.addAttribute("achievement", achievement);
        return "mt/achievement/form";
    }

    @RequiresPermissions("mt:achievement:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Achievement achievement, RedirectAttributes redirectAttributes) {
        achievementService.save(achievement);
        addMessage(redirectAttributes, "修改成功");
        return "redirect:" + adminPath + "/mt/achievement/achievementList?userId=" + achievement.getUserId();
    }

    @RequiresPermissions("mt:achievement:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        achievementService.delete(id);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/mt/achievement?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }
}
