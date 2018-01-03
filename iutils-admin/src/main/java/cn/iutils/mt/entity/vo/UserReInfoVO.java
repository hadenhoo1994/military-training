package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.UserReInfo;
import cn.iutils.sys.entity.DataEntity;

/**
* 用户及用户信息关联表
* @author iutils.cn
* @version 1.0
*/
public class UserReInfoVO extends UserReInfo {

    private static final long serialVersionUID = 1L;

    public UserReInfoVO(UserReInfo entity){
        super();
        setId(entity.getId());
        setUserInfoId(entity.getUserInfoId());
        setUserId(entity.getUserId());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
