package cn.iutils.mt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.CompanyDao;
import cn.iutils.mt.entity.Company;

/**
* 连表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class CompanyService extends CrudService<CompanyDao, Company> {

}
