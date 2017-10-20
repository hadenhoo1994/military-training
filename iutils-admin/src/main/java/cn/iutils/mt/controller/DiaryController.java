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
import cn.iutils.mt.entity.Diary;
import cn.iutils.mt.service.DiaryService;

/**
* 日记表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/mt/diary")
public class DiaryController extends BaseController {

    @Autowired
    private DiaryService diaryService;

    @ModelAttribute
    public Diary get(@RequestParam(required = false) String id) {
        Diary entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = diaryService.get(id);
        }
        if (entity == null) {
            entity = new Diary();
        }
        return entity;
    }

    @RequiresPermissions("mt:diary:view")
    @RequestMapping()
    public String list(Model model, Page<Diary> page) {
        model.addAttribute("page", page.setList(diaryService.findPage(page)));
        return "mt/diary/list";
    }

    @RequiresPermissions("mt:diary:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Diary diary,Model model) {
        model.addAttribute("diary", diary);
        return "mt/diary/form";
    }

    @RequiresPermissions("mt:diary:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Diary diary, RedirectAttributes redirectAttributes) {
        diaryService.save(diary);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/mt/diary/update?id="+diary.getId();
    }

    @RequiresPermissions("mt:diary:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Diary diary, Model model) {
        model.addAttribute("diary", diary);
        return "mt/diary/form";
    }

    @RequiresPermissions("mt:diary:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Diary diary, RedirectAttributes redirectAttributes) {
        diaryService.save(diary);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/mt/diary/update?id="+diary.getId();
    }

    @RequiresPermissions("mt:diary:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        diaryService.delete(id);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/mt/diary?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
