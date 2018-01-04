package cn.iutils.mt.controller.fly;

import cn.iutils.common.BaseController;
import cn.iutils.common.ResultJson;
import cn.iutils.mt.entity.Monent;
import cn.iutils.mt.entity.UserInfo;
import cn.iutils.mt.entity.vo.DiaryVO;
import cn.iutils.mt.service.DiaryService;
import cn.iutils.mt.service.MonentService;
import com.mzlion.core.json.fastjson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/1/4.
 */
@Controller
@RequestMapping("/flyDiary")
public class FlyDiaryController extends BaseController{
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private MonentService monentService;
    /**
     * 提交日记
     */
    @RequestMapping("/putDiary")
    @ResponseBody
    public String putDiary(DiaryVO diaryVO, HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");   //当前用户
       try {
           diaryService.putDiary(userInfo,diaryVO);
       }catch (Exception e){
           return JsonUtil.toJson(ResultJson.failureJson("300", "提交失败"));
       }
        return JsonUtil.toJson(ResultJson.successJson("200", "提交成功"));

    }

    /**
     * 提交心情
     */
    @RequestMapping("/putMonent")
    @ResponseBody
    public String putMonent(Monent monent, HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");   //当前用户
        Monent monentAdd = new Monent();
        monentAdd.setContent(monent.getContent());
        monentAdd.setImgUrl(monent.getImgUrl());
        monentAdd.setUserId(Integer.valueOf(userInfo.getId()));
        //保存
        monentService.saveOne(monentAdd);
        return JsonUtil.toJson(ResultJson.successJson("200", "发布心情成功"));
    }
}
