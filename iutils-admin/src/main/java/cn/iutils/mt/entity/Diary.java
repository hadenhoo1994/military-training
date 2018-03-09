package cn.iutils.mt.entity;

import cn.iutils.sys.entity.DataEntity;

import java.util.Date;

/**
* 日记表
* @author iutils.cn
* @version 1.0
*/
public class Diary extends DataEntity<Diary> {

    private static final long serialVersionUID = 1L;
private String userName;

    //
    private Integer userId;
    // 日记内容
    private String content;
    // 日记标题
    private String title;

    public Diary() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Diary(String id){
        super(id);
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    private Diary(Builder builder) {
        super();
        setId(builder.id);
        setUserId(builder.userId);
        setContent(builder.content);
        setTitle(builder.title);
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
        private String content;
        private String title;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Diary build() {
            return new Diary(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }


        public Builder content(String content) {
            this.content = content;
            return this;
        }


        public Builder title(String title) {
            this.title = title;
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
