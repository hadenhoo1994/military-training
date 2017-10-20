package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Classes;
import cn.iutils.sys.entity.DataEntity;

/**
* 班级表
* @author iutils.cn
* @version 1.0
*/
public class ClassesVO extends Classes {

    private static final long serialVersionUID = 1L;

    public ClassesVO(Classes entity){
        super();
        setId(entity.getId());
        setName(entity.getName());
        setProfessionId(entity.getProfessionId());
        setProfessionName(entity.getProfessionName());
        setDepartmentId(entity.getDepartmentId());
        setDepartmentName(entity.getDepartmentName());
        setGradeId(entity.getGradeId());
        setGradeName(entity.getGradeName());
        setStudentNum(entity.getStudentNum());
        setDirectorId(entity.getDirectorId());
        setDirectorName(entity.getDirectorName());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
