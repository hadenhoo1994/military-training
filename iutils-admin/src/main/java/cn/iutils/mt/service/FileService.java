package cn.iutils.mt.service;


import cn.iutils.common.service.BaseService;
import cn.iutils.common.utils.JFileUtils;
import cn.iutils.common.utils.StringUtils;
import cn.iutils.common.utils.oss.AliOSSApi;
import cn.iutils.common.utils.sequence.IdWorker;
import cn.iutils.mt.entity.vo.FileUploadVO;
import com.mzlion.core.io.FilenameUtils;
import jodd.io.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * 文件业务逻辑层
 * Created by Haden on 2017/8/8.
 */
@Service
public class FileService extends BaseService {
    /**
     * 上传一个文件
     *
     * @param multipartFile
     * @return
     * @throws FileNotFoundException
     */
    public FileUploadVO uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws IOException, InterruptedException {
        //1.上传文件
        FileUploadVO fileUploadVO = new FileUploadVO();
        File file = JFileUtils.MultipartFile2File(multipartFile);   //文件file
        //2.1获取文件后缀名,拼接文件名
        String extension = FilenameUtils.getFileExt(multipartFile.getOriginalFilename());   //文件后缀
        String fileName = IdWorker.getInstance().nextId() + "." + extension;        //拼接成文件名
        //将文件保存在本地
//        InputStream inputStream = new FileInputStream(file);
        //目录路径
        String path = request.getSession().getServletContext().getRealPath("/static/upload/" + fileName);
        FileUtil.copyFile(file, new File(path));
        fileUploadVO.setFileName(fileName);
        fileUploadVO.setUrl(path);
        return fileUploadVO;
    }
}


