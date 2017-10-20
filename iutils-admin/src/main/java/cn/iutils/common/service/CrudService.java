package cn.iutils.common.service;

import cn.iutils.common.BaseEntity;
import cn.iutils.common.ICrudDao;
import cn.iutils.common.Page;
import cn.iutils.common.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service基类
 *
 * @author cc
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends ICrudDao<T>, T extends BaseEntity<T>>
        extends BaseService {

    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T findOne(T entity) {
        return dao.findOne(entity);
    }

    /**
     * 查询列表数据
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询总数
     *
     * @return
     */
    public int count(Page<T> page) {
        return dao.count(page);
    }

    /**
     * 查询总数
     *
     * @param page
     * @param entity
     * @return
     */
    public int findCount(Page<T> page, T entity) {
        return dao.findCount(page, entity);
    }

    /**
     * 查询分页数据
     *
     * @param page
     * @return
     */
    public List<T> findPage(Page<T> page) {
        int count = dao.count(page);
        if (count > 0) {
            page.setTotal(count);
            return dao.findPage(page);
        }
        return null;
    }

    /**
     * 查询分页数据
     *
     * @param page
     * @param entity
     * @return
     */
    public List<T> findPage(Page page, T entity) {
        int count = dao.findCount(page, entity);
        if (count > 0) {
            page.setTotal(count);
            return dao.findPage(page, entity);
        }
        return null;
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public int save(T entity) {
        if (entity.getIsNewId()) {
            entity.preInsert();
            return dao.insert(entity);
        } else {
            entity.preUpdate();
            return dao.update(entity);
        }
    }

    /**
     * 保存数据（插入或更新[属性可为空值]）
     *
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int saveOne(T entity) {
        if (entity.getIsNewId()) {
            entity.preInsert();
            return dao.insertSelective(entity);
        } else {
            entity.preUpdate();
            return dao.insertSelective(entity);
        }
    }

    /**
     * 新增数据（属性可为空值）
     *
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int add(T entity) {
        entity.preInsert();
        return dao.insertSelective(entity);
    }

    /**
     * 修改数据（属性可为空值）
     *
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int modify(T entity) {
        entity.preUpdate();
        return dao.updateSelective(entity);
    }

    /**
     * 删除数据（修改删除状态）
     *
     * @return
     */
    @Transactional(readOnly = false)
    public int remove(T entity) {
        entity.preUpdate();
        entity.setStatus(Constant.STATUS_DELETE);
        return dao.updateSelective(entity);
    }

    /**
     * 删除数据
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public int delete(T entity) {
        return dao.delete(entity);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    @Transactional(readOnly = false)
    public int delete(String id) {
        return dao.delete(id);
    }

}
