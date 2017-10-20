package cn.iutils.mt.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.mt.entity.Profession;

/**
* 专业表 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface ProfessionDao extends ICrudDao<Profession> {

}
