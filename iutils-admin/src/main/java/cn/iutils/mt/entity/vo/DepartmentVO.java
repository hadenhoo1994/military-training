package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Department;

/**
 * 系表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class DepartmentVO extends Department {

    private static final long serialVersionUID = 1L;

    public DepartmentVO(Department entity) {
        super();
        setId(entity.getId());
        setName(entity.getName());
        setGradeId(entity.getGradeId());
        setGradeName(entity.getGradeName());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
