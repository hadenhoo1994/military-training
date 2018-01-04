package cn.iutils.mt.entity.vo;

/**
 * 文件上传返回的数据视图类
 * Created by Haden on 2017/8/9.
 */
public class FileUploadVO {
    private String url;
    private String fileName;

    public FileUploadVO() {
    }

    public FileUploadVO(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
