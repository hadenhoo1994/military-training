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

    // 营id
    private Integer battalionId;
    // 连id
    private Integer companyId;
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
    // 周几 1-7
    private Integer days;
    // 节次
    private Integer classes1;
    // 节次
    private Integer classes2;
    // 节次
    private Integer classes3;
    // 节次
    private Integer classes4;
    // 节次
    private Integer classes5;
    // 节次
    private Integer classes6;
    // 节次
    private Integer classes7;
    // 节次
    private Integer classes8;
    // 节次
    private Integer classes9;
    // 节次
    private Integer classes10;
    // 节次
    private Integer classes11;

    public Course() {
        super();
    }

    public Course(String id){
        super(id);
    }

    public Integer getBattalionId(){
        return battalionId;
    }

    public void setBattalionId(Integer battalionId){
        this.battalionId = battalionId;
    }

    public Integer getCompanyId(){
        return companyId;
    }

    public void setCompanyId(Integer companyId){
        this.companyId = companyId;
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

    public Integer getDays(){
        return days;
    }

    public void setDays(Integer days){
        this.days = days;
    }

    public Integer getClasses1(){
        return classes1;
    }

    public void setClasses1(Integer classes1){
        this.classes1 = classes1;
    }

    public Integer getClasses2(){
        return classes2;
    }

    public void setClasses2(Integer classes2){
        this.classes2 = classes2;
    }

    public Integer getClasses3(){
        return classes3;
    }

    public void setClasses3(Integer classes3){
        this.classes3 = classes3;
    }

    public Integer getClasses4(){
        return classes4;
    }

    public void setClasses4(Integer classes4){
        this.classes4 = classes4;
    }

    public Integer getClasses5(){
        return classes5;
    }

    public void setClasses5(Integer classes5){
        this.classes5 = classes5;
    }

    public Integer getClasses6(){
        return classes6;
    }

    public void setClasses6(Integer classes6){
        this.classes6 = classes6;
    }

    public Integer getClasses7(){
        return classes7;
    }

    public void setClasses7(Integer classes7){
        this.classes7 = classes7;
    }

    public Integer getClasses8(){
        return classes8;
    }

    public void setClasses8(Integer classes8){
        this.classes8 = classes8;
    }

    public Integer getClasses9(){
        return classes9;
    }

    public void setClasses9(Integer classes9){
        this.classes9 = classes9;
    }

    public Integer getClasses10(){
        return classes10;
    }

    public void setClasses10(Integer classes10){
        this.classes10 = classes10;
    }

    public Integer getClasses11(){
        return classes11;
    }

    public void setClasses11(Integer classes11){
        this.classes11 = classes11;
    }

    private Course(Builder builder) {
        super();
        setId(builder.id);
        setBattalionId(builder.battalionId);
        setCompanyId(builder.companyId);
        setPlatoonId(builder.platoonId);
        setPlatoonName(builder.platoonName);
        setProjectId(builder.projectId);
        setProjectName(builder.projectName);
        setWeek(builder.week);
        setDays(builder.days);
        setClasses1(builder.classes1);
        setClasses2(builder.classes2);
        setClasses3(builder.classes3);
        setClasses4(builder.classes4);
        setClasses5(builder.classes5);
        setClasses6(builder.classes6);
        setClasses7(builder.classes7);
        setClasses8(builder.classes8);
        setClasses9(builder.classes9);
        setClasses10(builder.classes10);
        setClasses11(builder.classes11);
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
        private Integer battalionId;
        private Integer companyId;
        private Integer platoonId;
        private String platoonName;
        private Integer projectId;
        private String projectName;
        private Integer week;
        private Integer days;
        private Integer classes1;
        private Integer classes2;
        private Integer classes3;
        private Integer classes4;
        private Integer classes5;
        private Integer classes6;
        private Integer classes7;
        private Integer classes8;
        private Integer classes9;
        private Integer classes10;
        private Integer classes11;
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


        public Builder battalionId(Integer battalionId) {
            this.battalionId = battalionId;
            return this;
        }


        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
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


        public Builder days(Integer days) {
            this.days = days;
            return this;
        }


        public Builder classes1(Integer classes1) {
            this.classes1 = classes1;
            return this;
        }


        public Builder classes2(Integer classes2) {
            this.classes2 = classes2;
            return this;
        }


        public Builder classes3(Integer classes3) {
            this.classes3 = classes3;
            return this;
        }


        public Builder classes4(Integer classes4) {
            this.classes4 = classes4;
            return this;
        }


        public Builder classes5(Integer classes5) {
            this.classes5 = classes5;
            return this;
        }


        public Builder classes6(Integer classes6) {
            this.classes6 = classes6;
            return this;
        }


        public Builder classes7(Integer classes7) {
            this.classes7 = classes7;
            return this;
        }


        public Builder classes8(Integer classes8) {
            this.classes8 = classes8;
            return this;
        }


        public Builder classes9(Integer classes9) {
            this.classes9 = classes9;
            return this;
        }


        public Builder classes10(Integer classes10) {
            this.classes10 = classes10;
            return this;
        }


        public Builder classes11(Integer classes11) {
            this.classes11 = classes11;
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
