package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 专业表
* @author iutils.cn
* @version 1.0
*/
public class Profession extends DataEntity<Profession> {

    private static final long serialVersionUID = 1L;

    // 专业名称
    private String name;
    // 专业所属系id
    private Integer departmentId;
    // 专业所属系名称
    private String departmentName;
    // 第几届id
    private Integer gradeId;
    // 第几届
    private String gradeName;

    public Profession() {
        super();
    }

    public Profession(String id){
        super(id);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
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

    public Integer getGradeId(){
        return gradeId;
    }

    public void setGradeId(Integer gradeId){
        this.gradeId = gradeId;
    }

    public String getGradeName(){
        return gradeName;
    }

    public void setGradeName(String gradeName){
        this.gradeName = gradeName;
    }

    private Profession(Builder builder) {
        super();
        setId(builder.id);
        setName(builder.name);
        setDepartmentId(builder.departmentId);
        setDepartmentName(builder.departmentName);
        setGradeId(builder.gradeId);
        setGradeName(builder.gradeName);
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
        private String name;
        private Integer departmentId;
        private String departmentName;
        private Integer gradeId;
        private String gradeName;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Profession build() {
            return new Profession(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
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


        public Builder gradeId(Integer gradeId) {
            this.gradeId = gradeId;
            return this;
        }


        public Builder gradeName(String gradeName) {
            this.gradeName = gradeName;
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
