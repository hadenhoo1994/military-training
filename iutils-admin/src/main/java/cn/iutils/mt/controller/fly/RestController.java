package cn.iutils.mt.controller.fly;

import cn.iutils.common.BaseController;
import cn.iutils.common.ResultJson;
import cn.iutils.mt.entity.*;
import cn.iutils.mt.service.*;
import com.mzlion.core.json.fastjson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 接口数据请求控制器
 * Created by Administrator on 2018/1/8.
 */
@Controller
@RequestMapping("/rest")
public class RestController extends BaseController {
    @Autowired
    private MonentService monentService;
    @Autowired
    private BattalionService battalionService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PlatoonService platoonService;
    @Autowired
    private ProjectService projectService;

    /**
     * 点赞接口
     *
     * @param id
     * @return
     */
    @RequestMapping("/monentLike")
    @ResponseBody
    public String monentLike(String id) {
        Monent monent = monentService.get(id);
        if (monent == null) {
            return JsonUtil.toJson(ResultJson.failureJson("300", "该心情已不存在或已被删除", "提交失败"));
        }
        //赞数+1
        monent.setLikeNum(monent.getLikeNum() + 1);
        //保存
        monentService.modify(monent);
        return JsonUtil.toJson(ResultJson.successJson("200", "点赞成功"));
    }

    /**
     * 测试rest页面
     *
     * @return
     */
    @RequestMapping("/test")
    public String testRestPage() {
        return "testPage";
    }

    /**
     * 获取营
     *
     * @return
     */
    @RequestMapping("/getBattalion")
    @ResponseBody
    public String getBattalion() {
        List<Battalion> battalion = battalionService.findList(new Battalion());
        return JsonUtil.toJson(ResultJson.successJson("200", battalion));
    }

    //课程
    @ResponseBody
    @RequestMapping(value = "/getProject")
    public String getProject() {
        List<Project> projects = projectService.findList(new Project());
        return   JsonUtil.toJson(ResultJson.successJson("200", projects));
    }


    /**
     * 获取连
     *
     * @param battalionId
     * @return
     */
    @RequestMapping("/getCompany")
    @ResponseBody
    public String getCompany(String battalionId) {
        Company company = new Company();
        company.setBattalionId(Integer.valueOf(battalionId));
        List<Company> battalion = companyService.findList(company);
        return JsonUtil.toJson(ResultJson.successJson("200", battalion));
    }

    /**
     * 获取排
     *
     * @param companyId
     * @return
     */
    @RequestMapping("/getPlatoon")
    @ResponseBody
    public String getPlatoon(String companyId) {
        Platoon platoon = new Platoon();
        platoon.setCompanyId(Integer.valueOf(companyId));
        List<Platoon> battalion = platoonService.findList(platoon);
        return JsonUtil.toJson(ResultJson.successJson("200", battalion));
    }

}
