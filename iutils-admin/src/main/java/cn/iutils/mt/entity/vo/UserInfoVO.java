package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.UserInfo;

/**
 * 用户表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class UserInfoVO extends UserInfo {

    private static final long serialVersionUID = 1L;

    public UserInfoVO(UserInfo entity) {
        super();
        setId(entity.getId());
        setName(entity.getName());
        setFullName(entity.getFullName());
        setGender(entity.getGender());
        setClassId(entity.getClassId());
        setClassName(entity.getClassName());
        setProfessionId(entity.getProfessionId());
        setProfessionName(entity.getProfessionName());
        setDepartmentId(entity.getDepartmentId());
        setDepartmentName(entity.getDepartmentName());
        setPlatoonId(entity.getPlatoonId());
        setPlatoonName(entity.getPlatoonName());
        setCompanyId(entity.getCompanyId());
        setCompanyName(entity.getCompanyName());
        setBattalionId(entity.getBattalionId());
        setBattalionName(entity.getBattalionName());
        setMobileNumber(entity.getMobileNumber());
        setQq(entity.getQq());
        setWx(entity.getWx());
        setImgUrl(entity.getImgUrl());
        setAddress(entity.getAddress());
        setIdentity(entity.getIdentity());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
