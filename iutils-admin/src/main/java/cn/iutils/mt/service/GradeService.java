package cn.iutils.mt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.GradeDao;
import cn.iutils.mt.entity.Grade;

/**
* 届表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class GradeService extends CrudService<GradeDao, Grade> {

}
