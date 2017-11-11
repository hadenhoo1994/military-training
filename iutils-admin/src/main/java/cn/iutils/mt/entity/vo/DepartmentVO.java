package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Department;
import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 系表
* @author iutils.cn
* @version 1.0
*/
public class DepartmentVO extends Department {

    private static final long serialVersionUID = 1L;

    public DepartmentVO(Department entity){
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
