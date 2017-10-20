package cn.iutils.mt.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.mt.entity.Department;

/**
* 系表 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface DepartmentDao extends ICrudDao<Department> {

}
