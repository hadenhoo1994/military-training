package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Diary;
import cn.iutils.mt.entity.DiaryImg;

import java.util.List;

/**
 * 日记表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class DiaryVO extends Diary {
    private String img1;
    private String img2;
    private String img3;
    private List<DiaryImg> imgs;

    public List<DiaryImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<DiaryImg> imgs) {
        this.imgs = imgs;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    private static final long serialVersionUID = 1L;

    public DiaryVO(){}

    public DiaryVO(Diary entity) {
        super();
        setTitle(entity.getTitle());
        setContent(entity.getContent());
        setId(entity.getId());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
