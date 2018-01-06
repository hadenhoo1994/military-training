package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Monent;
import cn.iutils.mt.entity.UserInfo;

/**
* 动态表
* @author iutils.cn
* @version 1.0
*/
public class MonentVO extends Monent {
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private static final long serialVersionUID = 1L;
    public MonentVO(){}

    public MonentVO(Monent entity){
        super();
        setId(entity.getId());
        setUserId(entity.getUserId());
        setUserName(entity.getUserName());
        setContent(entity.getContent());
        setImgUrl(entity.getImgUrl());
        setLikeNum(entity.getLikeNum());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }



}
