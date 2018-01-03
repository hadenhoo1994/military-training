package cn.iutils.mt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.UserReInfoDao;
import cn.iutils.mt.entity.UserReInfo;

/**
* 用户及用户信息关联表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class UserReInfoService extends CrudService<UserReInfoDao, UserReInfo> {

}
