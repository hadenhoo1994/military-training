package cn.iutils.sys.controller;

import cn.iutils.common.Page;
import cn.iutils.common.utils.UserUtils;
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
    public String update(Organization organization,
                         RedirectAttributes redirectAttributes) {
        organizationService.save(organization);
        addMessage(redirectAttributes, "修改成功");
        return "redirect:" + adminPath + "/organization/update?id=" + organization.getId();
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
