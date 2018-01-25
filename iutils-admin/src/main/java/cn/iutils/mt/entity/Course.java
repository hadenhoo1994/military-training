package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 排课表
* @author iutils.cn
* @version 1.0
*/
public class Course extends DataEntity<Course> {

    private static final long serialVersionUID = 1L;

    // 排id
    private Integer platoonId;
    // 排名称
    private String platoonName;
    // 课程id
    private Integer projectId;
    // 课程名称
    private String projectName;
    // 周次
    private Integer week;
    // 节次
    private Integer classes;

    public Course() {
        super();
    }

    public Course(String id){
        super(id);
    }

    public Integer getPlatoonId(){
        return platoonId;
    }

    public void setPlatoonId(Integer platoonId){
        this.platoonId = platoonId;
    }

    public String getPlatoonName(){
        return platoonName;
    }

    public void setPlatoonName(String platoonName){
        this.platoonName = platoonName;
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

    public Integer getWeek(){
        return week;
    }

    public void setWeek(Integer week){
        this.week = week;
    }

    public Integer getClasses(){
        return classes;
    }

    public void setClasses(Integer classes){
        this.classes = classes;
    }

    private Course(Builder builder) {
        super();
        setId(builder.id);
        setPlatoonId(builder.platoonId);
        setPlatoonName(builder.platoonName);
        setProjectId(builder.projectId);
        setProjectName(builder.projectName);
        setWeek(builder.week);
        setClasses(builder.classes);
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
        private Integer platoonId;
        private String platoonName;
        private Integer projectId;
        private String projectName;
        private Integer week;
        private Integer classes;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Course build() {
            return new Course(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder platoonId(Integer platoonId) {
            this.platoonId = platoonId;
            return this;
        }


        public Builder platoonName(String platoonName) {
            this.platoonName = platoonName;
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


        public Builder week(Integer week) {
            this.week = week;
            return this;
        }


        public Builder classes(Integer classes) {
            this.classes = classes;
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
