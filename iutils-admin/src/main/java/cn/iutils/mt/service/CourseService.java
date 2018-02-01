package cn.iutils.mt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.CourseDao;
import cn.iutils.mt.entity.Course;

/**
* 排课表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class CourseService extends CrudService<CourseDao, Course> {

    public int getCountCourseProject(String id) {
        int i = dao.getCountCourseProject(id);
        return i;
    }
}
