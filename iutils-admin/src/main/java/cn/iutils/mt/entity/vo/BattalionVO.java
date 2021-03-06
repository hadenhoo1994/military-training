package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Battalion;

/**
* 营表
* @author iutils.cn
* @version 1.0
*/
public class BattalionVO extends Battalion {

    private static final long serialVersionUID = 1L;

    public BattalionVO(Battalion entity){
        super();
        setId(entity.getId());
        setName(entity.getName());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
