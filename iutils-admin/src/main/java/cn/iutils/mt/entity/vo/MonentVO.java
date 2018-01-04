package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Monent;
import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 动态表
* @author iutils.cn
* @version 1.0
*/
public class MonentVO extends Monent {

    private static final long serialVersionUID = 1L;

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
