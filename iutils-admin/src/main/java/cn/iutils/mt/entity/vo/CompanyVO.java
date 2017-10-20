package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Company;

/**
* 连表
* @author iutils.cn
* @version 1.0
*/
public class CompanyVO extends Company {

    private static final long serialVersionUID = 1L;

    public CompanyVO(Company entity){
        super();
        setId(entity.getId());
        setName(entity.getName());
        setBattalionId(entity.getBattalionId());
        setBattalionName(entity.getBattalionName());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
