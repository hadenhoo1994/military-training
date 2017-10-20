package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 用户表
* @author iutils.cn
* @version 1.0
*/
public class UserInfo extends DataEntity<UserInfo> {

    private static final long serialVersionUID = 1L;

    // 姓名
    private String name;
    // 班级全称
    private String fullName;
    // 性别 0:男 1:女
    private Integer gender;
    // 班级id
    private Integer classId;
    // 班级名称
    private String className;
    // 专业id
    private Integer professionId;
    // 专业名称
    private String professionName;
    // 系别id
    private Integer departmentId;
    // 系别名称
    private String departmentName;
    // 排id
    private Integer platoonId;
    // 排名称
    private String platoonName;
    // 连id
    private Integer companyId;
    // 连名称
    private String companyName;
    // 营id
    private Integer battalionId;
    // 营名称
    private String battalionName;
    // 手机号码
    private String mobileNumber;
    // qq号码
    private String qq;
    // 微信号
    private String wx;
    // 头像
    private String imgUrl;
    // 住址
    private String address;
    // 用户身份标识 0:学生 1:教师 2:教官
    private Integer identity;

    public UserInfo() {
        super();
    }

    public UserInfo(String id){
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

    public Integer getGender(){
        return gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;
    }

    public Integer getClassId(){
        return classId;
    }

    public void setClassId(Integer classId){
        this.classId = classId;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
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

    public String getMobileNumber(){
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public String getQq(){
        return qq;
    }

    public void setQq(String qq){
        this.qq = qq;
    }

    public String getWx(){
        return wx;
    }

    public void setWx(String wx){
        this.wx = wx;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public Integer getIdentity(){
        return identity;
    }

    public void setIdentity(Integer identity){
        this.identity = identity;
    }

    private UserInfo(Builder builder) {
        super();
        setId(builder.id);
        setName(builder.name);
        setFullName(builder.fullName);
        setGender(builder.gender);
        setClassId(builder.classId);
        setClassName(builder.className);
        setProfessionId(builder.professionId);
        setProfessionName(builder.professionName);
        setDepartmentId(builder.departmentId);
        setDepartmentName(builder.departmentName);
        setPlatoonId(builder.platoonId);
        setPlatoonName(builder.platoonName);
        setCompanyId(builder.companyId);
        setCompanyName(builder.companyName);
        setBattalionId(builder.battalionId);
        setBattalionName(builder.battalionName);
        setMobileNumber(builder.mobileNumber);
        setQq(builder.qq);
        setWx(builder.wx);
        setImgUrl(builder.imgUrl);
        setAddress(builder.address);
        setIdentity(builder.identity);
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
        private Integer gender;
        private Integer classId;
        private String className;
        private Integer professionId;
        private String professionName;
        private Integer departmentId;
        private String departmentName;
        private Integer platoonId;
        private String platoonName;
        private Integer companyId;
        private String companyName;
        private Integer battalionId;
        private String battalionName;
        private String mobileNumber;
        private String qq;
        private String wx;
        private String imgUrl;
        private String address;
        private Integer identity;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public UserInfo build() {
            return new UserInfo(this);
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


        public Builder gender(Integer gender) {
            this.gender = gender;
            return this;
        }


        public Builder classId(Integer classId) {
            this.classId = classId;
            return this;
        }


        public Builder className(String className) {
            this.className = className;
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


        public Builder platoonId(Integer platoonId) {
            this.platoonId = platoonId;
            return this;
        }


        public Builder platoonName(String platoonName) {
            this.platoonName = platoonName;
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


        public Builder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }


        public Builder qq(String qq) {
            this.qq = qq;
            return this;
        }


        public Builder wx(String wx) {
            this.wx = wx;
            return this;
        }


        public Builder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }


        public Builder address(String address) {
            this.address = address;
            return this;
        }


        public Builder identity(Integer identity) {
            this.identity = identity;
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
