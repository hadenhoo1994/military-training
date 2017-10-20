package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 军排表
* @author iutils.cn
* @version 1.0
*/
public class Platoon extends DataEntity<Platoon> {

    private static final long serialVersionUID = 1L;

    // 排名称
    private String name;
    // 排全称
    private String fullName;
    // 连id
    private Integer companyId;
    // 连名称
    private String companyName;
    // 营id
    private Integer battalionId;
    // 营名称
    private String battalionName;
    // 学生数
    private Integer studentNum;

    public Platoon() {
        super();
    }

    public Platoon(String id){
        super(id);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public Integer getCompanyId(){
        return companyId;
    }

    public void setCompanyId(Integer companyId){
        this.companyId = companyId;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
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

    public Integer getStudentNum(){
        return studentNum;
    }

    public void setStudentNum(Integer studentNum){
        this.studentNum = studentNum;
    }

    private Platoon(Builder builder) {
        super();
        setId(builder.id);
        setName(builder.name);
        setFullName(builder.fullName);
        setCompanyId(builder.companyId);
        setCompanyName(builder.companyName);
        setBattalionId(builder.battalionId);
        setBattalionName(builder.battalionName);
        setStudentNum(builder.studentNum);
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
        private String fullName;
        private Integer companyId;
        private String companyName;
        private Integer battalionId;
        private String battalionName;
        private Integer studentNum;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Platoon build() {
            return new Platoon(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }


        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }


        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }


        public Builder companyName(String companyName) {
            this.companyName = companyName;
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


        public Builder studentNum(Integer studentNum) {
            this.studentNum = studentNum;
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
