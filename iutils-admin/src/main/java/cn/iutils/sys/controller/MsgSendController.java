package cn.iutils.sys.controller;

import cn.iutils.common.utils.UserUtils;
import cn.iutils.sys.entity.MsgReceive;
import cn.iutils.sys.service.MsgReceiveService;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import cn.iutils.sys.entity.MsgSend;
import cn.iutils.sys.service.MsgSendService;

import java.util.Date;
import java.util.List;

/**
 * 消息发送表 控制器
 *
 * @author iutils.cn
 * @version 1.0
 */
@Controller
@RequestMapping("${adminPath}/sys/msgSend")
public class MsgSendController extends BaseController {

    @Autowired
    private MsgSendService msgSendService;

    @Autowired
    private MsgReceiveService msgReceiveService;

    @ModelAttribute
    public MsgSend get(@RequestParam(required = false) String id) {
        MsgSend entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = msgSendService.get(id);
        }
        if (entity == null) {
            entity = new MsgSend();
        }
        return entity;
    }

    @RequestMapping()
    public String list(MsgSend msgSend, Model model, Page<MsgSend> page) {
        msgSend.setUser(UserUtils.getLoginUser());
        page.setEntity(msgSend);
        page.setOrderBy("a.create_date desc,a.level desc");
        model.addAttribute("page", page.setList(msgSendService.findPage(page)));
        return "sys/msgSend/list";
    }

    @RequiresPermissions("sys:msgSend:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(MsgSend msgSend, Model model) {
        model.addAttribute("msgSend", msgSend);
        return "sys/msgSend/form";
    }

    /**
     * 发送信息
     *
     * @param users
     * @param msgSend
     * @param redirectAttributes
     * @return
     */
    @Transactional(readOnly = false)
    @RequiresPermissions("sys:msgSend:create")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(String[] users, MsgSend msgSend, RedirectAttributes redirectAttributes) {
        msgSend.setStatus("1");//已发送
        msgSendService.save(msgSend);
        List<MsgReceive> msgReceives = Lists.newArrayList();
        for (int i = 0; i < users.length; i++) {
            if ("".equals(users[i].trim())) {
                continue;
            }
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setMsgId(msgSend.getId());
            msgReceive.setUpdateBy(users[i]);
            msgReceive.setUpdateDate(new Date());
            msgReceive.setStatus("0");
            msgReceives.add(msgReceive);
        }
        if (msgReceives.size() > 0) {
            msgReceiveService.saveBatch(msgReceives);
            addMessage(redirectAttributes, "发送成功");
        }
        return "redirect:" + adminPath + "/sys/msgSend/create?id=" + msgSend.getId();
    }

    /**
     * 保存草稿
     *
     * @param msgSend
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:msgSend:create")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(MsgSend msgSend, RedirectAttributes redirectAttributes) {
        msgSend.setStatus("0");//草稿
        msgSendService.save(msgSend);
        addMessage(redirectAttributes, "保存草稿成功");
        return "redirect:" + adminPath + "/sys/msgSend/create?id=" + msgSend.getId();
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(MsgSend msgSend, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        msgSendService.delete(msgSend);
        msgReceiveService.deleteByMsgId(msgSend.getId());//删除对应的关系
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/sys/msgSend?type=" + msgSend.getType() + "&status=" + msgSend.getStatus() + "&pageNo=" + pageNo + "&pageSize=" + pageSize;
    }
}
