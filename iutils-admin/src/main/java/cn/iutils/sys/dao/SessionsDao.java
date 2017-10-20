package cn.iutils.sys.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.sys.entity.Sessions;

/**
 * session管理 DAO接口
 *
 * @author iutils.cn
 * @version 1.0
 */
@MyBatisDao
public interface SessionsDao extends ICrudDao<Sessions> {

}
