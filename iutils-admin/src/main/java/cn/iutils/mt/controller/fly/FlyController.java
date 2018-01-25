package cn.iutils.mt.controller.fly;

import cn.iutils.common.BaseController;
import cn.iutils.common.Page;
import cn.iutils.common.ResultJson;
import cn.iutils.mt.entity.Diary;
import cn.iutils.mt.entity.Monent;
import cn.iutils.mt.entity.UserInfo;
import cn.iutils.mt.entity.vo.DiaryVO;
import cn.iutils.mt.entity.vo.MonentVO;
import cn.iutils.mt.service.DiaryService;
import cn.iutils.mt.service.MonentService;
import cn.iutils.mt.service.UserInfoService;
import com.mzlion.core.json.fastjson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志内容 军训图片上传等
 * Created by Administrator on 2018/1/3.
 */
@Controller
@RequestMapping("/fly")
public class FlyController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private MonentService monentService;
    @Autowired
    private DiaryService diaryService;

    /**
     * 首页
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping()
    public String list(Model model, HttpServletRequest request, Page<Monent> monentPage) {
        //当前登录用户
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            model.addAttribute("user", null);
        }
        model.addAttribute("user", userInfo);
        //首页显示心情
        //1.查询出来monent
        boolean flag = false;
        if (monentPage.getPageNo() >0){
            monentPage.setPageNo(monentPage.getPageNo()-1);
            flag = true;
        }
        List<Monent> monentList = monentService.findPage(monentPage, new Monent());
        //拼入用户信息
        List<MonentVO> monentVOS = new ArrayList<>();
        if (monentList.size() < 1) {
            model.addAttribute("list", null);
        }else{
            for (Monent monent : monentList) {
                MonentVO monentVO = new MonentVO(monent);
                monentVO.setUserInfo(userInfoService.get(monent.getUserId().toString()));
                monentVOS.add(monentVO);
            }
            model.addAttribute("list", monentVOS);
        }
        monentPage.setTotal(monentService.findCount(monentPage, new Monent()));
        if (flag){
            monentPage.setPageNo(monentPage.getPageNo()+1);
        }
        model.addAttribute("page", monentPage);
        return "fly/html/index";
    }

    /**
     * 用户登录页面
     */
    @RequestMapping("/login")
    public String userLoginPage() {
        return "fly/html/user/login";
    }

    /**
     * 用户注册页面
     */
    @RequestMapping("reg")
    public String reg() {
        return "fly/html/user/reg";
    }

    /**
     * 上传日记页面
     */
    @RequestMapping("/my/addDiary")
    public String addDiary(Model model, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            return "fly/html/login";
        }
        model.addAttribute("user", userInfo);
        return "fly/html/jie/addDiary";
    }

    /**
     * 上传心情页面
     */
    @RequestMapping("/my/addMonent")
    public String addMonent(Model model, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            return "fly/html/login";
        }
        model.addAttribute("user", userInfo);
        return "fly/html/jie/addMonent";
    }

    /**
     * 我的日记
     */
    @RequestMapping("/my/diary")
    public String myMiary(Model model, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            return "fly/html/login";
        }
        //我的日记内容
        model.addAttribute("user", userInfo);
        List<DiaryVO> diaryVOList = diaryService.getMyDiary(userInfo.getId());
        model.addAttribute("diaryList", diaryVOList);
        return "fly/html/user/diary";
    }

    /**
     * 我的心情
     */
    @RequestMapping("/my/monent")
    public String myMonent(Model model, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            return "fly/html/login";
        }
        //我的心情列表
        model.addAttribute("user", userInfo);
        List<Monent> monentList = monentService.findList(Monent.newBuilder().userId(Integer.valueOf(userInfo.getId())).build());
        model.addAttribute("monentList", monentList);
        return "fly/html/user/monent";
    }


    /**
     * 用户登录
     *
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping("/users/login")
    @ResponseBody
    public String userLogin(UserInfo userInfo, HttpServletRequest request) {
        UserInfo user = userInfoService.login(userInfo);
        if (user == null) {
            //登录失败
            return JsonUtil.toJson(ResultJson.failureJson("300", "用户名或密码不正确"));
        }
        request.getSession().setAttribute("userInfo", user);
        return JsonUtil.toJson(ResultJson.failureJson("200", "登录成功"));
    }

    /**
     * 注册
     *
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping("/users/reg")
    @ResponseBody
    public String reg(UserInfo userInfo, String repsw, HttpServletRequest request) {
        if (!repsw.equals(userInfo.getPassword())) {
            return JsonUtil.toJson(ResultJson.failureJson("300", "验证密码和密码不相同"));
        }
        if (userInfoService.findUserByPhone(userInfo.getMobileNumber())) {
            //该手机号码已存在
            return JsonUtil.toJson(ResultJson.failureJson("300", "该手机号码已存在"));
        }
        UserInfo addUser = new UserInfo();
        addUser.setMobileNumber(userInfo.getMobileNumber());
        addUser.setPassword(userInfo.getPassword());
        addUser.setName(userInfo.getName());
        userInfoService.saveOne(addUser);
        addUser = userInfoService.get(addUser.getId());
        request.getSession().setAttribute("userInfo", addUser);
        return JsonUtil.toJson(ResultJson.failureJson("200", "注册成功"));
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("userInfo");
        return "redirect:/fly";
    }


}
