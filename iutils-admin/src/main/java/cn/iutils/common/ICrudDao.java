package cn.iutils.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO支持类实现
 *
 * @param <T>
 * @author cc
 */
public interface ICrudDao<T> extends IBaseDao {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T get(T entity);

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T findOne(T entity);

    /**
     * 查询数据列表
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * 查询总数
     *
     * @return
     */
    public int count(@Param("page") Page<T> page);

    /**
     * 查询总数
     *
     * @param page
     * @param entity
     * @return
     */
    public int findCount(@Param("page") Page<T> page, @Param("entity") T entity);

    /**
     * 查询分页数据
     *
     * @param page
     * @return
     */
    public List<T> findPage(@Param("page") Page<T> page);

    /**
     * 查询分页数据
     *
     * @param page
     * @param entity
     * @return
     */
    public List<T> findPage(@Param("page") Page<T> page, @Param("entity") T entity);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * 插入数据（属性可为空值）
     *
     * @param entity
     * @return
     */
    public int insertSelective(T entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * 更新数据（属性可为空值）
     *
     * @param entity
     * @return
     */
    public int updateSelective(T entity);

    /**
     * 删除数据
     *
     * @param entity
     * @return
     */
    public int delete(T entity);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public int delete(String id);

}