package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Project;

/**
 * 军训项目表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class ProjectVO extends Project {

    private static final long serialVersionUID = 1L;

    public ProjectVO(Project entity) {
        super();
        setId(entity.getId());
        setProjectName(entity.getProjectName());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
