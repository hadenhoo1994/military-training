package cn.iutils.sys.controller;

import cn.iutils.common.BaseController;
import cn.iutils.common.ResultVo;
import cn.iutils.common.utils.UserUtils;
import cn.iutils.sys.entity.User;
import cn.iutils.sys.service.PasswordHelper;
import cn.iutils.sys.service.UserService;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器
 *
 * @author cc
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class LoginController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 获取异步登录界面
     *
     * @param response
     * @return
     */
    @RequestMapping("/ajaxLogin")
    public String ajaxLogin(HttpServletResponse response) {
        ResultVo resultVo = null;
        //获取用户登录信息 验证已登录，返回登录信息
        String userName = UserUtils.getLoginUserName();
        if (userName != null) {
            resultVo = new ResultVo(ResultVo.SUCCESS, "1", "登录成功", null);
            return renderString(response, resultVo);
        } else {
            return "ajaxLogin";
        }
    }

    /**
     * 登录处理
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        //ajax登录失败处理
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            ResultVo resultVo = new ResultVo(ResultVo.FAILURE, "0", "登录失败", null);
            return renderString(response, resultVo);
        }
        //获取用户登录信息 验证已登录，跳转到管理页
        String userName = UserUtils.getLoginUserName();
        if (userName != null) {
            return "redirect:" + adminPath;
        }
        String exceptionClassName = (String) request
                .getAttribute("shiroLoginFailure");
        model.addAttribute("username", request.getParameter("username"));
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(
                exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (ExcessiveAttemptsException.class.getName().equals(
                exceptionClassName)) {
            error = "错误次数操作超过限制";
        } else if (LockedAccountException.class.getName().equals(
                exceptionClassName)) {
            error = "帐号被锁定";
        } else if ("jCaptcha.error".equals(exceptionClassName)) {
            error = "验证码错误";
        } else if (exceptionClassName != null) {
            error = "未知的错误";
        }
        model.addAttribute("msg", error);
        if (request.getParameter("forceLogout") != null) {
            error = "您已经被管理员强制退出，请重新登录";
            model.addAttribute("msg", error);
        }
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @param rpassword 确认密码
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String password, String rpassword, Model model) {
        String url = "register";
        //判断是否密码重复
        if (!password.equals(rpassword)) {
            addMessage(model, "两次密码不一致");
        } else {
            User user = userService.getUserByUserName(username);
            if (user == null) {
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setOrganizationId("1");
                user.setRoleIdsStr("3,");
                passwordHelper.encryptPassword(user);
                userService.save(user);
                addMessage(model, "注册成功");
                url = "login";
            } else {
                addMessage(model, "账号已存在");
            }
        }
        return url;
    }

}
