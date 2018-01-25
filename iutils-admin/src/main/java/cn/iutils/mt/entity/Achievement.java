package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 成绩表
* @author iutils.cn
* @version 1.0
*/
public class Achievement extends DataEntity<Achievement> {

    private static final long serialVersionUID = 1L;

    // 用户id
    private Integer userId;
    // 用户名称
    private String userName;
    // 课程id
    private Integer projectId;
    // 课程名称
    private String projectName;
    // 分数
    private Double num;

    public Achievement() {
        super();
    }

    public Achievement(String id){
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

    public Integer getProjectId(){
        return projectId;
    }

    public void setProjectId(Integer projectId){
        this.projectId = projectId;
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public Double getNum(){
        return num;
    }

    public void setNum(Double num){
        this.num = num;
    }

    private Achievement(Builder builder) {
        super();
        setId(builder.id);
        setUserId(builder.userId);
        setUserName(builder.userName);
        setProjectId(builder.projectId);
        setProjectName(builder.projectName);
        setNum(builder.num);
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
        private Integer projectId;
        private String projectName;
        private Double num;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Achievement build() {
            return new Achievement(this);
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


        public Builder projectId(Integer projectId) {
            this.projectId = projectId;
            return this;
        }


        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }


        public Builder num(Double num) {
            this.num = num;
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
