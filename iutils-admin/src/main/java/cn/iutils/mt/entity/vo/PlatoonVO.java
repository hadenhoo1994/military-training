package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Platoon;

/**
 * 军排表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class PlatoonVO extends Platoon{

private static final long serialVersionUID=1L;

public PlatoonVO(Platoon entity){
        super();
        setId(entity.getId());
        setName(entity.getName());
        setFullName(entity.getFullName());
        setCompanyId(entity.getCompanyId());
        setCompanyName(entity.getCompanyName());
        setBattalionId(entity.getBattalionId());
        setBattalionName(entity.getBattalionName());
        setStudentNum(entity.getStudentNum());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
        }

        }
