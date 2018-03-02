package cn.iutils.sys.controller;

import cn.iutils.common.Page;
import cn.iutils.common.utils.UserUtils;
import cn.iutils.mt.entity.Course;
import cn.iutils.mt.service.CourseService;
import com.mzlion.core.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.iutils.common.BaseController;
import cn.iutils.common.utils.JStringUtils;
import cn.iutils.sys.entity.Organization;
import cn.iutils.sys.service.OrganizationService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 组织机构控制器
 *
 * @author cc
 */
@Controller
@RequestMapping("${adminPath}/organization")
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CourseService courseService;

    @ModelAttribute
    public Organization get(@RequestParam(required = false) String id) {
        Organization entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = organizationService.get(id);
        }
        if (entity == null) {
            entity = new Organization();
        }
        return entity;
    }

    /**
     * 组织机构列表页
     *
     * @param organization
     * @param model
     * @return
     */
    @RequiresPermissions("sys:organization:view")
    @RequestMapping()
    public String list(Organization organization, Model model, Page<Organization> page) {
        Organization organization1 = new Organization();
        organization1.setUser(UserUtils.getLoginUser());
        List<Organization> organizationList = organizationService.findList(organization1);
        model.addAttribute("organizationList", organizationList);
        //初始化加载第一个
        if (JStringUtils.isBlank(organization.getId()) && organizationList.size() > 0) {
            organization.setId(organizationList.get(0).getId());
        }
        //如果选择的机构大于当前用户所在的机构，默认用当前用户的机构
        if (Integer.valueOf(organization.getId()) < Integer.valueOf(UserUtils.getLoginUser().getOrganizationId())) {
            organization = UserUtils.getLoginUser().getOrganization();
        }
        page.setEntity(organization);
        model.addAttribute("page", page.setList(organizationService.findPage(page)));
        return "sys/organization/list";
    }

    /**
     * 跳转到新增页
     *
     * @param organization
     * @param model
     * @return
     */
    @RequiresPermissions("sys:organization:edit")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Organization organization, Model model) {
        model.addAttribute("organization", organization);
        return "sys/organization/add";
    }

    /**
     * 跳转到修改页
     *
     * @param organization
     * @param model
     * @return
     */
    @RequiresPermissions("sys:organization:edit")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Organization organization, Model model) {
        organization.setUser(UserUtils.getLoginUser());
        model.addAttribute("organizationList", organizationService.findList(organization));
        model.addAttribute("organization", organization);
        return "sys/organization/edit";
    }

    /**
     * 修改
     *
     * @param organization
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:organization:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Organization organization, RedirectAttributes redirectAttributes) {
        boolean flag = false;
        if (organization.getIsNewId()) {
            flag = true;
        }
        organizationService.save(organization);
        if (flag) {      //如果是新的排机构
            int count = StringUtils.getSubCount(organization.getParentIds(), "/");
            if (count == 4) {    //排级
                //为当前排生成5天的初始课表
                createCourse(organization);
            }
        }
        addMessage(redirectAttributes, "修改成功");
        return "redirect:" + adminPath + "/organization/update?id=" + organization.getId();
    }

    /**
     * 创建初始课表
     *
     * @param organization
     */
    private void createCourse(Organization organization) {
        for (int i = 1; i <= 11; i++) {
            Course course = new Course();
            course.setOrganizationId(organization.getId());
            course.setPlatoonName(organization.getName());
            course.setDays(i);      //节次1-11
            //周一至周五
            course.setClasses1("军体拳");
            course.setClasses2("军体拳");
            course.setClasses3("军体拳");
            course.setClasses4("军体拳");
            course.setClasses5("军体拳");

            courseService.save(course);
        }
    }



    /**
     * 删除
     *
     * @param organization
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:organization:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Organization organization, int pageNo, int pageSize,
                         RedirectAttributes redirectAttributes) {
        if (!"1".equals(organization.getId())) {
            //删除前判断是否存在下级节点
            int count = organizationService.findNext(organization);
            if (count > 0) {
                addMessage(redirectAttributes, "删除失败，存在子节点，请先删除子节点");
            } else {
                organizationService.delete(organization);
                addMessage(redirectAttributes, "删除成功");
            }
        } else {
            addMessage(redirectAttributes, "超级管理员才能删除");
        }
        return "redirect:" + adminPath + "/organization?id=" + organization.getParentId() + "&pageNo=" + pageNo + "&pageSize=" + pageSize;
    }
}
