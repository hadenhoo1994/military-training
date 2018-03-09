package cn.iutils.mt.controller;

import cn.iutils.common.utils.UserUtils;
import cn.iutils.mt.entity.DiaryImg;
import cn.iutils.mt.entity.UserInfo;
import cn.iutils.mt.entity.vo.DiaryVO;
import cn.iutils.mt.service.DiaryImgService;
import cn.iutils.mt.service.UserInfoService;
import cn.iutils.sys.entity.User;
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
import cn.iutils.mt.entity.Diary;
import cn.iutils.mt.service.DiaryService;

import java.util.ArrayList;
import java.util.List;

/**
 * 日记表 控制器
 *
 * @author iutils.cn
 * @version 1.0
 */
@Controller
@RequestMapping("${adminPath}/mt/diary")
public class DiaryController extends BaseController {

    @Autowired
    private DiaryService diaryService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private DiaryImgService diaryImgService;

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
        User loginUser = UserUtils.getLoginUser();
        boolean nomUser = isNomUser(loginUser);
        List<Diary> newDiarys = new ArrayList<>();
        List<Diary> diaries;
        if (nomUser) {       //是普通用户
            //只能看到自己发的日记
            UserInfo userInfo = userInfoService.findOne(UserInfo.newBuilder().mobileNumber(loginUser.getMobile()).build());
            diaries = diaryService.findPage(page, Diary.newBuilder().userId(Integer.valueOf(userInfo.getId())).build());
        } else {
            diaries = diaryService.findPage(page, new Diary());
        }
        for (Diary d : diaries
                ) {
            String userName = userInfoService.get(String.valueOf(d.getUserId())).getName();
            d.setUserName(userName);
            newDiarys.add(d);
        }
        model.addAttribute("page", page.setList(newDiarys));
        return "mt/diary/list";
    }

    /**
     * 判断是否是普通用户权限
     *
     * @param loginUser
     * @return
     */
    private boolean isNomUser(User loginUser) {
        boolean flag = false;
        if (loginUser != null) {
            List<String> roles = loginUser.getRoleIds();
            for (String s : roles
                    ) {
                if (s.equals("2")) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    @RequiresPermissions("mt:diary:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Diary diary, Model model) {
        model.addAttribute("diary", diary);
        return "mt/diary/form";
    }

    @RequiresPermissions("mt:diary:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Diary diary, RedirectAttributes redirectAttributes) {
        diaryService.save(diary);
        addMessage(redirectAttributes, "新增成功");
        return "redirect:" + adminPath + "/mt/diary/update?id=" + diary.getId();
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
        addMessage(redirectAttributes, "修改成功");
        return "redirect:" + adminPath + "/mt/diary/update?id=" + diary.getId();
    }

    @RequiresPermissions("mt:diary:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        diaryService.delete(id);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/mt/diary?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }

    @RequestMapping(value = "/DiaryDetail")
    public String DiaryDetail(String id,Model model){
        Diary diary = diaryService.get(id);
        List<DiaryImg> diaryImgList = diaryImgService.findList(DiaryImg.newBuilder().diaryId(Integer.valueOf(diary.getId())).build());
        DiaryVO diaryVO = new DiaryVO(diary);
        diaryVO.setImgs(diaryImgList);
        model.addAttribute("diary", diaryVO);
        return "/fly/html/jie/diaryDetail";
    }
}
