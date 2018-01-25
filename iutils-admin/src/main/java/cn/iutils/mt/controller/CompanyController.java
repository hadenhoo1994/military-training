package cn.iutils.mt.controller;

import cn.iutils.mt.entity.Platoon;
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
import cn.iutils.mt.entity.Company;
import cn.iutils.mt.service.CompanyService;

/**
* 连表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/company")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;

    @ModelAttribute
    public Company get(@RequestParam(required = false) String id) {
        Company entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = companyService.get(id);
        }
        if (entity == null) {
            entity = new Company();
        }
        return entity;
    }

    @RequiresPermissions("mt:company:view")
    @RequestMapping()
    public String list(Model model, Page<Company> page) {
        model.addAttribute("page", page.setList(companyService.findPage(page,new Company())));
        return "mt/company/list";
    }

    @RequiresPermissions("mt:company:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Company company,Model model) {
        model.addAttribute("company", company);
        return "mt/company/form";
    }

    @RequiresPermissions("mt:company:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Company company, RedirectAttributes redirectAttributes) {
        if (companyService.findOne(Company.newBuilder().battalionName(company.getBattalionName()).name(company.getName()).build()) != null) {
            addMessage(redirectAttributes,"该连已存在");
            return "redirect:"+ adminPath +"/mt/company/update?id="+company.getId();
        }
        companyService.save(company);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/company/update?id="+company.getId();
    }

    @RequiresPermissions("mt:company:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Company company, Model model) {
        model.addAttribute("company", company);
        return "mt/company/form";
    }

    @RequiresPermissions("mt:company:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Company company, RedirectAttributes redirectAttributes) {
        if (companyService.findOne(Company.newBuilder().battalionName(company.getBattalionName()).name(company.getName()).build()) != null) {
            addMessage(redirectAttributes,"该连已存在");
            return "redirect:"+ adminPath +"/mt/company/update?id="+company.getId();
        }
        companyService.save(company);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/company/update?id="+company.getId();
    }

    @RequiresPermissions("mt:company:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        companyService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/company?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
