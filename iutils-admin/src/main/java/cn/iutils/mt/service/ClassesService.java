package cn.iutils.mt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.ClassesDao;
import cn.iutils.mt.entity.Classes;

/**
* 班级表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class ClassesService extends CrudService<ClassesDao, Classes> {

}
