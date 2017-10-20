package cn.iutils.sys.dao;

import org.apache.ibatis.annotations.Param;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.sys.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户Dao接口
 *
 * @author cc
 */
@MyBatisDao
public interface IUserDao extends ICrudDao<User> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    public User getUserByUserName(@Param("username") String username);

    /**
     * 获取用户列表
     *
     * @param users
     * @return
     */
    public List<Map> getUsers(@Param("users") String[] users);

}
