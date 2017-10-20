package cn.iutils.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.sys.dao.IOrganizationDao;
import cn.iutils.sys.entity.Organization;

/**
 * 组织机构服务
 *
 * @author cc
 */
@Service
@Transactional(readOnly = true)
public class OrganizationService extends CrudService<IOrganizationDao, Organization> {

    /**
     * 查询是否存在下级节点
     *
     * @param organization
     * @return
     */
    public int findNext(Organization organization) {
        return dao.findNext(organization);
    }

}
