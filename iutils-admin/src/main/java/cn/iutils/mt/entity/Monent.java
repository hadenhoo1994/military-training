package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 动态表
* @author iutils.cn
* @version 1.0
*/
public class Monent extends DataEntity<Monent> {

    private static final long serialVersionUID = 1L;

    // 
    private Integer userId;
    // 
    private String userName;
    // 
    private String content;
    // 
    private String imgUrl;
    // 
    private Integer likeNum;

    public Monent() {
        super();
    }

    public Monent(String id){
        super(id);
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public Integer getLikeNum(){
        return likeNum;
    }

    public void setLikeNum(Integer likeNum){
        this.likeNum = likeNum;
    }

    private Monent(Builder builder) {
        super();
        setId(builder.id);
        setUserId(builder.userId);
        setUserName(builder.userName);
        setContent(builder.content);
        setImgUrl(builder.imgUrl);
        setLikeNum(builder.likeNum);
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
        private Integer userId;
        private String userName;
        private String content;
        private String imgUrl;
        private Integer likeNum;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Monent build() {
            return new Monent(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }


        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }


        public Builder content(String content) {
            this.content = content;
            return this;
        }


        public Builder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }


        public Builder likeNum(Integer likeNum) {
            this.likeNum = likeNum;
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
