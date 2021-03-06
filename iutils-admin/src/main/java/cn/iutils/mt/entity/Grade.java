package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 届表
* @author iutils.cn
* @version 1.0
*/
public class Grade extends DataEntity<Grade> {

    private static final long serialVersionUID = 1L;

    // 系id
    private Integer departmentId;
    // 系名称
    private String departmentName;
    // 届名称
    private String name;

    public Grade() {
        super();
    }

    public Grade(String id){
        super(id);
    }

    public Integer getDepartmentId(){
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId){
        this.departmentId = departmentId;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private Grade(Builder builder) {
        super();
        setId(builder.id);
        setDepartmentId(builder.departmentId);
        setDepartmentName(builder.departmentName);
        setName(builder.name);
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
        private Integer departmentId;
        private String departmentName;
        private String name;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Grade build() {
            return new Grade(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder departmentId(Integer departmentId) {
            this.departmentId = departmentId;
            return this;
        }


        public Builder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
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
