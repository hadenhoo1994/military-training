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
import cn.iutils.mt.entity.Achievement;
import cn.iutils.mt.service.AchievementService;

/**
* 成绩表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/achievement")
public class AchievementController extends BaseController {

    @Autowired
    private AchievementService achievementService;

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
    public String list(Model model, Page<Achievement> page) {
        model.addAttribute("page", page.setList(achievementService.findPage(page)));
        return "mt/achievement/list";
    }

    @RequiresPermissions("mt:achievement:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Achievement achievement,Model model) {
        model.addAttribute("achievement", achievement);
        return "mt/achievement/form";
    }

    @RequiresPermissions("mt:achievement:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Achievement achievement, RedirectAttributes redirectAttributes) {
        achievementService.save(achievement);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/achievement/update?id="+achievement.getId();
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
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/achievement/update?id="+achievement.getId();
    }

    @RequiresPermissions("mt:achievement:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        achievementService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/achievement?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
