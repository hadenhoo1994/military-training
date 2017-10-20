package cn.iutils.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.sys.dao.SessionsDao;
import cn.iutils.sys.entity.Sessions;

/**
 * session管理 Service层
 *
 * @author iutils.cn
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SessionService extends CrudService<SessionsDao, Sessions> {

    /**
     * 新增会话
     *
     * @param sessions
     * @return
     */
    @Transactional(readOnly = false)
    public int add(Sessions sessions) {
        return dao.insert(sessions);
    }

    /**
     * 更新会话
     *
     * @param sessions
     * @return
     */
    @Transactional(readOnly = false)
    public int update(Sessions sessions) {
        return dao.update(sessions);
    }

}
