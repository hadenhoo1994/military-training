package cn.iutils.mt.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.mt.entity.Course;
import org.apache.ibatis.annotations.Param;

/**
* 排课表 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface CourseDao extends ICrudDao<Course> {

    int getCountCourseProject(@Param("id") String id);
}
