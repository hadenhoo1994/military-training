package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Grade;

/**
 * 届表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class GradeVO extends Grade {

    private static final long serialVersionUID = 1L;

    public GradeVO(Grade entity) {
        super();
        setId(entity.getId());
        setDepartmentId(entity.getDepartmentId());
        setDepartmentName(entity.getDepartmentName());
        setName(entity.getName());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
