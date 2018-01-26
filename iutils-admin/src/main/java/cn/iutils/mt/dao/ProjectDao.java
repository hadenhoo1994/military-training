package cn.iutils.mt.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.mt.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 军训项目表 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface ProjectDao extends ICrudDao<Project> {

    List<Project> getProjectByNameOrNumber(@Param("name") String name);
}
