package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 班级表
* @author iutils.cn
* @version 1.0
*/
public class Classes extends DataEntity<Classes> {

    private static final long serialVersionUID = 1L;

    // 班级名称
    private String name;
    // 班级所属专业id
    private Integer professionId;
    // 班级所属专业名称
    private String professionName;
    // 班级所属系id
    private Integer departmentId;
    // 班级所属系名称
    private String departmentName;
    // 第几届id
    private Integer gradeId;
    // 第几届
    private String gradeName;
    // 班级学生数
    private Integer studentNum;
    // 班级班主任id
    private Integer directorId;
    // 班级班主任名称
    private String directorName;

    public Classes() {
        super();
    }

    public Classes(String id){
        super(id);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getProfessionId(){
        return professionId;
    }

    public void setProfessionId(Integer professionId){
        this.professionId = professionId;
    }

    public String getProfessionName(){
        return professionName;
    }

    public void setProfessionName(String professionName){
        this.professionName = professionName;
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

    public Integer getStudentNum(){
        return studentNum;
    }

    public void setStudentNum(Integer studentNum){
        this.studentNum = studentNum;
    }

    public Integer getDirectorId(){
        return directorId;
    }

    public void setDirectorId(Integer directorId){
        this.directorId = directorId;
    }

    public String getDirectorName(){
        return directorName;
    }

    public void setDirectorName(String directorName){
        this.directorName = directorName;
    }

    private Classes(Builder builder) {
        super();
        setId(builder.id);
        setName(builder.name);
        setProfessionId(builder.professionId);
        setProfessionName(builder.professionName);
        setDepartmentId(builder.departmentId);
        setDepartmentName(builder.departmentName);
        setGradeId(builder.gradeId);
        setGradeName(builder.gradeName);
        setStudentNum(builder.studentNum);
        setDirectorId(builder.directorId);
        setDirectorName(builder.directorName);
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
        private Integer professionId;
        private String professionName;
        private Integer departmentId;
        private String departmentName;
        private Integer gradeId;
        private String gradeName;
        private Integer studentNum;
        private Integer directorId;
        private String directorName;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Classes build() {
            return new Classes(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }


        public Builder professionId(Integer professionId) {
            this.professionId = professionId;
            return this;
        }


        public Builder professionName(String professionName) {
            this.professionName = professionName;
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


        public Builder studentNum(Integer studentNum) {
            this.studentNum = studentNum;
            return this;
        }


        public Builder directorId(Integer directorId) {
            this.directorId = directorId;
            return this;
        }


        public Builder directorName(String directorName) {
            this.directorName = directorName;
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
