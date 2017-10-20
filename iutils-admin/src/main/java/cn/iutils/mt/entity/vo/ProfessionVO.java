package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Profession;

/**
 * 专业表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class ProfessionVO extends Profession {

    private static final long serialVersionUID = 1L;

    public ProfessionVO(Profession entity) {
        super();
        setId(entity.getId());
        setName(entity.getName());
        setDepartmentId(entity.getDepartmentId());
        setDepartmentName(entity.getDepartmentName());
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
