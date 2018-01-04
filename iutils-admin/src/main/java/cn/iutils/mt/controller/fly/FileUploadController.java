package cn.iutils.mt.controller.fly;

import cn.iutils.common.config.Constant;
import cn.iutils.mt.entity.vo.FileUploadVO;
import cn.iutils.mt.service.FileService;
import com.mzlion.core.json.fastjson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件上传控制器
 * Created by Administrator on 2018/1/4.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile multfile, HttpServletRequest request){
        if (multfile == null) {
            return JsonUtil.toJsonFail(Constant.FAILURE_CODE, "文件不能为空");
        }
        FileUploadVO flVO = null;
        try {
            flVO = fileService.uploadFile(multfile,request);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (flVO.getUrl() == null) {
            return JsonUtil.toJsonFail(Constant.FAILURE_CODE, "文件上传失败");
        }
        return JsonUtil.toJsonSuccess("文件上传成功", flVO);
    }
}
