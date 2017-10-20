package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 连表
* @author iutils.cn
* @version 1.0
*/
public class Company extends DataEntity<Company> {

    private static final long serialVersionUID = 1L;

    // 连名称
    private String name;
    // 营id
    private Integer battalionId;
    // 营名称
    private String battalionName;

    public Company() {
        super();
    }

    public Company(String id){
        super(id);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getBattalionId(){
        return battalionId;
    }

    public void setBattalionId(Integer battalionId){
        this.battalionId = battalionId;
    }

    public String getBattalionName(){
        return battalionName;
    }

    public void setBattalionName(String battalionName){
        this.battalionName = battalionName;
    }

    private Company(Builder builder) {
        super();
        setId(builder.id);
        setName(builder.name);
        setBattalionId(builder.battalionId);
        setBattalionName(builder.battalionName);
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
        private Integer battalionId;
        private String battalionName;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Company build() {
            return new Company(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }


        public Builder battalionId(Integer battalionId) {
            this.battalionId = battalionId;
            return this;
        }


        public Builder battalionName(String battalionName) {
            this.battalionName = battalionName;
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
