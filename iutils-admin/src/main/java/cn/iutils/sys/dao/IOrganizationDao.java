package cn.iutils.sys.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.sys.entity.Organization;

/**
 * 组织机构Dao
 *
 * @author cc
 */
@MyBatisDao
public interface IOrganizationDao extends ICrudDao<Organization> {

    /**
     * 查询是否存在下级节点
     *
     * @param organization
     * @return
     */
    public int findNext(Organization organization);

}
