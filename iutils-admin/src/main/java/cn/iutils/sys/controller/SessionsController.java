package cn.iutils.sys.controller;

import cn.iutils.common.config.JConfig;
import cn.iutils.common.utils.SerializableUtils;
import cn.iutils.sys.dao.IDBSessionDAO;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cn.iutils.common.Page;
import cn.iutils.common.utils.JStringUtils;
import cn.iutils.common.BaseController;
import cn.iutils.sys.entity.Sessions;
import cn.iutils.sys.service.SessionService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * session管理 控制器
 *
 * @author iutils.cn
 * @version 1.0
 */
@Controller
@RequestMapping("${adminPath}/sys/session")
public class SessionsController extends BaseController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private IDBSessionDAO sessionDAO;

    @ModelAttribute
    public Sessions get(@RequestParam(required = false) String id) {
        Sessions entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = sessionService.get(id);
        }
        if (entity == null) {
            entity = new Sessions();
        }
        return entity;
    }

    @RequiresPermissions("sys:session:view")
    @RequestMapping()
    public String list(Model model, Page<Sessions> page) {
        page.setOrderBy("a.create_date desc");
        page.setList(sessionService.findPage(page));
        List<Session> list = Lists.newArrayList();
        for (int i = 0; i < page.getList().size(); i++) {
            Session session = SerializableUtils.deserialize(page.getList().get(i).getSession());
            list.add(session);
        }
        Page<Session> pages = new Page<Session>(page.getPageNo(), page.getPageSize(), page.getTotal(), list);
        model.addAttribute("page", pages);
        return "sys/session/list";
    }

    /**
     * 强制退出
     *
     * @param pageNo
     * @param pageSize
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:session:forceLogout")
    @RequestMapping(value = "/{sessionId}/forceLogout", method = RequestMethod.GET)
    public String delete(@PathVariable("sessionId") String sessionId, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        Session session = sessionDAO.readSession(sessionId);
        if (session != null) {
            session.setAttribute(JConfig.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
        }
        addMessage(redirectAttributes, "强制退出成功");
        return "redirect:" + adminPath + "/sys/session?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }
}
