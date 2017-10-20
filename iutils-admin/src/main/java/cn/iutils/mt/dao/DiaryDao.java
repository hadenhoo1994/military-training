package cn.iutils.mt.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.mt.entity.Diary;

/**
* 日记表 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface DiaryDao extends ICrudDao<Diary> {

}
