package cn.iutils.mt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.MonentDao;
import cn.iutils.mt.entity.Monent;

/**
* 动态表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class MonentService extends CrudService<MonentDao, Monent> {

}
