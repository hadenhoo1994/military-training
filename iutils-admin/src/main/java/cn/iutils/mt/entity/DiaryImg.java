package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 日记图片表
* @author iutils.cn
* @version 1.0
*/
public class DiaryImg extends DataEntity<DiaryImg> {

    private static final long serialVersionUID = 1L;

    // 日记id(可为null)
    private Integer diaryId;
    // 图片URL地址
    private String imgUrl;

    public DiaryImg() {
        super();
    }

    public DiaryImg(String id){
        super(id);
    }

    public Integer getDiaryId(){
        return diaryId;
    }

    public void setDiaryId(Integer diaryId){
        this.diaryId = diaryId;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    private DiaryImg(Builder builder) {
        super();
        setId(builder.id);
        setDiaryId(builder.diaryId);
        setImgUrl(builder.imgUrl);
        setCreateBy(builder.createBy);
        setCreateDate(builder.createDate);
        setUpdateBy(builder.updateBy);
        setUpdateDate(builder.updateDate);
        setRemarks(builder.remarks);
        if (builder.status != null && builder.status.length() != 0) {
            setStatus(builder.status);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private Integer diaryId;
        private String imgUrl;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public DiaryImg build() {
            return new DiaryImg(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder diaryId(Integer diaryId) {
            this.diaryId = diaryId;
            return this;
        }


        public Builder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }


        public Builder createBy(String createBy) {
            this.createBy = createBy;
            return this;
        }


        public Builder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }


        public Builder updateBy(String updateBy) {
            this.updateBy = updateBy;
            return this;
        }


        public Builder updateDate(Date updateDate) {
            this.updateDate = updateDate;
            return this;
        }


        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }


        public Builder status(String status) {
            this.status = status;
            return this;
        }

    }

}
