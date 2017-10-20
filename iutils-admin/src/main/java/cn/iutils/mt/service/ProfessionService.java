package cn.iutils.mt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.ProfessionDao;
import cn.iutils.mt.entity.Profession;

/**
* 专业表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class ProfessionService extends CrudService<ProfessionDao, Profession> {

}
