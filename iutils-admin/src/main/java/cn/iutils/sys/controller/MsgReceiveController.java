package cn.iutils.sys.controller;

import cn.iutils.common.utils.UserUtils;
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
import cn.iutils.sys.entity.MsgReceive;
import cn.iutils.sys.service.MsgReceiveService;

/**
 * 消息接收表 控制器
 *
 * @author iutils.cn
 * @version 1.0
 */
@Controller
@RequestMapping("${adminPath}/sys/msgReceive")
public class MsgReceiveController extends BaseController {

    @Autowired
    private MsgReceiveService msgReceiveService;

    @ModelAttribute
    public MsgReceive get(@RequestParam(required = false) String id) {
        MsgReceive entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = msgReceiveService.get(id);
        }
        if (entity == null) {
            entity = new MsgReceive();
        }
        return entity;
    }

    /**
     * 获取消息列表
     *
     * @param model
     * @param page
     * @return
     */
    @RequestMapping()
    public String list(MsgReceive msgReceive, Model model, Page<MsgReceive> page) {
        msgReceive.setUser(UserUtils.getLoginUser());
        page.setEntity(msgReceive);
        page.setOrderBy("a.update_date desc,c.level desc");
        model.addAttribute("page", page.setList(msgReceiveService.findPage(page)));
        return "sys/msgReceive/list";
    }

    /**
     * 查看详细
     *
     * @param msgReceive
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(MsgReceive msgReceive, Model model) {
        msgReceive.setStatus("1");
        msgReceiveService.save(msgReceive);
        model.addAttribute("msgReceive", msgReceive);
        return "sys/msgReceive/form";
    }

    /**
     * 删除 消息
     *
     * @param msgReceive
     * @param pageNo
     * @param pageSize
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(MsgReceive msgReceive, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        msgReceiveService.delete(msgReceive);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/sys/msgReceive?msgSend.type=" + msgReceive.getMsgSend().getType() + "&status=" + msgReceive.getStatus() + "&pageNo=" + pageNo + "&pageSize=" + pageSize;
    }
}
