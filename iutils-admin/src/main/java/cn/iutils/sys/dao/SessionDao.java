package cn.iutils.sys.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.sys.entity.Session;

/**
 * session管理 DAO接口
 *
 * @author iutils.cn
 * @version 1.0
 */
@MyBatisDao
public interface SessionDao extends ICrudDao<Session> {

}
