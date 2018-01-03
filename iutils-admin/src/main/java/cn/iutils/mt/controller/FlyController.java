package cn.iutils.mt.controller;

import cn.iutils.common.BaseController;
import cn.iutils.common.ResultJson;
import cn.iutils.mt.entity.UserInfo;
import cn.iutils.mt.service.UserInfoService;
import com.mzlion.core.json.fastjson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志内容 军训图片上传等
 * Created by Administrator on 2018/1/3.
 */
@Controller
@RequestMapping("/fly")
public class FlyController extends BaseController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping()
    public String list(Model model, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo == null){
            return "fly/html/index";
        }
        model.addAttribute("user", userInfo);
        return "fly/html/index";
    }

    /**
     * 用户登录页面
     */
    @RequestMapping("/login")
    public String userLoginPage(){
        return "fly/html/user/login";
    }
    /**
     * 用户注册页面
     */
    @RequestMapping("reg")
    public String reg(){
        return "fly/html/user/reg";
    }
    /**
     * 上传日记页面
     */
    @RequestMapping("/my/addDiary")
    public String addDiary(){
        return "fly/html/jie/add";
    }


    /**
     * 用户登录
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping("/users/login")
    @ResponseBody
    public String userLogin(UserInfo userInfo,  HttpServletRequest request){
        UserInfo user = userInfoService.login(userInfo);
        if (user == null){
            //登录失败
            return JsonUtil.toJson(ResultJson.failureJson("300", "用户名或密码不正确"));
        }
        request.getSession().setAttribute("userInfo", user);
        return JsonUtil.toJson(ResultJson.failureJson("200", "登录成功"));
    }

    /**
     * 注册
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping("/users/reg")
    @ResponseBody
    public String reg(UserInfo userInfo,String repsw,  HttpServletRequest request){
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
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("userInfo");
        return "redirect:/fly";
    }


}
