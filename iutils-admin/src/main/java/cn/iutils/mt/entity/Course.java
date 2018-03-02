package cn.iutils.mt.entity;

import cn.iutils.sys.entity.DataEntity;

import java.util.Date;

/**
 * 排课表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class Course extends DataEntity<Course> {

    private static final long serialVersionUID = 1L;

    private String organizationId;
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
    // 节数
    private Integer days;
    // 周1
    private String classes1;
    // 周2
    private String classes2;
    // 周3
    private String classes3;
    // 周4
    private String classes4;
    // 周5
    private String classes5;
    // 节次
    private String classes6;
    // 节次
    private String classes7;
    // 节次
    private String classes8;
    // 节次
    private String classes9;
    // 节次
    private String classes10;
    // 节次
    private String classes11;

    public Course() {
        super();
    }

    public Course(String id) {
        super(id);
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getBattalionId() {
        return battalionId;
    }

    public void setBattalionId(Integer battalionId) {
        this.battalionId = battalionId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getPlatoonId() {
        return platoonId;
    }

    public void setPlatoonId(Integer platoonId) {
        this.platoonId = platoonId;
    }

    public String getPlatoonName() {
        return platoonName;
    }

    public void setPlatoonName(String platoonName) {
        this.platoonName = platoonName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getClasses1() {
        return classes1;
    }

    public void setClasses1(String classes1) {
        this.classes1 = classes1;
    }

    public String getClasses2() {
        return classes2;
    }

    public void setClasses2(String classes2) {
        this.classes2 = classes2;
    }

    public String getClasses3() {
        return classes3;
    }

    public void setClasses3(String classes3) {
        this.classes3 = classes3;
    }

    public String getClasses4() {
        return classes4;
    }

    public void setClasses4(String classes4) {
        this.classes4 = classes4;
    }

    public String getClasses5() {
        return classes5;
    }

    public void setClasses5(String classes5) {
        this.classes5 = classes5;
    }

    public String getClasses6() {
        return classes6;
    }

    public void setClasses6(String classes6) {
        this.classes6 = classes6;
    }

    public String getClasses7() {
        return classes7;
    }

    public void setClasses7(String classes7) {
        this.classes7 = classes7;
    }

    public String getClasses8() {
        return classes8;
    }

    public void setClasses8(String classes8) {
        this.classes8 = classes8;
    }

    public String getClasses9() {
        return classes9;
    }

    public void setClasses9(String classes9) {
        this.classes9 = classes9;
    }

    public String getClasses10() {
        return classes10;
    }

    public void setClasses10(String classes10) {
        this.classes10 = classes10;
    }

    public String getClasses11() {
        return classes11;
    }

    public void setClasses11(String classes11) {
        this.classes11 = classes11;
    }

    private Course(Builder builder) {
        super();
        setId(builder.id);
        setOrganizationId(builder.organizationId);
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
        private String organizationId;
        private Integer battalionId;
        private Integer companyId;
        private Integer platoonId;
        private String platoonName;
        private Integer projectId;
        private String projectName;
        private Integer week;
        private Integer days;
        private String classes1;
        private String classes2;
        private String classes3;
        private String classes4;
        private String classes5;
        private String classes6;
        private String classes7;
        private String classes8;
        private String classes9;
        private String classes10;
        private String classes11;
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

        public Builder organizationId(String organizationId) {
            this.organizationId = organizationId;
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


        public Builder classes1(String classes1) {
            this.classes1 = classes1;
            return this;
        }


        public Builder classes2(String classes2) {
            this.classes2 = classes2;
            return this;
        }


        public Builder classes3(String classes3) {
            this.classes3 = classes3;
            return this;
        }


        public Builder classes4(String classes4) {
            this.classes4 = classes4;
            return this;
        }


        public Builder classes5(String classes5) {
            this.classes5 = classes5;
            return this;
        }


        public Builder classes6(String classes6) {
            this.classes6 = classes6;
            return this;
        }


        public Builder classes7(String classes7) {
            this.classes7 = classes7;
            return this;
        }


        public Builder classes8(String classes8) {
            this.classes8 = classes8;
            return this;
        }


        public Builder classes9(String classes9) {
            this.classes9 = classes9;
            return this;
        }


        public Builder classes10(String classes10) {
            this.classes10 = classes10;
            return this;
        }


        public Builder classes11(String classes11) {
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
