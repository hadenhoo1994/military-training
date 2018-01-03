package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 用户及用户信息关联表
* @author iutils.cn
* @version 1.0
*/
public class UserReInfo extends DataEntity<UserReInfo> {

    private static final long serialVersionUID = 1L;

    // 
    private Integer userInfoId;
    // 
    private Integer userId;

    public UserReInfo() {
        super();
    }

    public UserReInfo(String id){
        super(id);
    }

    public Integer getUserInfoId(){
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId){
        this.userInfoId = userInfoId;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    private UserReInfo(Builder builder) {
        super();
        setId(builder.id);
        setUserInfoId(builder.userInfoId);
        setUserId(builder.userId);
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
        private Integer userInfoId;
        private Integer userId;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public UserReInfo build() {
            return new UserReInfo(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder userInfoId(Integer userInfoId) {
            this.userInfoId = userInfoId;
            return this;
        }


        public Builder userId(Integer userId) {
            this.userId = userId;
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
