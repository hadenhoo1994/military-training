package cn.iutils.mt.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.mt.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 用户表 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface UserInfoDao extends ICrudDao<UserInfo> {

    /**
     * 根据用户名或编号获取用户
     * @param name
     * @return
     */
    List<UserInfo> getStudentByNameOrNumber(@Param("name") String name);
}
